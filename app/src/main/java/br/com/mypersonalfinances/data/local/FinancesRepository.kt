package br.com.mypersonalfinances.data.local

import br.com.mypersonalfinances.R
import br.com.mypersonalfinances.presenter.ExtractCardModel

class FinancesRepository(
    private val financesDAO: FinancesDAO
) {

    suspend fun getAll(): List<Transaction> {
        return financesDAO.getAll()
    }

    suspend fun insert(transaction: Transaction) {
        financesDAO.insert(transaction)
    }

    suspend fun delete(id: String) {
        financesDAO.delete(id)
    }

    fun convertTransactionToExtractCardList(transactionList: List<Transaction>): List<ExtractCardModel> {
        val extractCardModelList = mutableListOf<ExtractCardModel>()

        transactionList.forEach { transaction ->
            extractCardModelList.add(
                ExtractCardModel(
                    description = transaction.description,
                    amount = transaction.amount.toString(),
                    category = transaction.category.toString(),
                    date = transaction.date.toString(),
                    backgroundColor = getBackground(transaction)
                )
            )
        }
        return extractCardModelList
    }

    private fun getBackground(transaction: Transaction): Int {
        return when (transaction.transactionType) {
            TransactionType.INCOME -> R.color.soft_green
            TransactionType.EXPENSE -> R.color.soft_red
        }
    }
}
