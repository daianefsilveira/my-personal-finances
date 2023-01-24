package br.com.mypersonalfinances.data.local

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import br.com.mypersonalfinances.R
import br.com.mypersonalfinances.presenter.ExtractCardModel
import br.com.mypersonalfinances.presenter.HomeCardModel

class FinancesRepository(private val application: Application) {

    private val financesDAO: FinancesDAO

    init {
        val db = FinancesDataBase.getDatabase(application)
        financesDAO = db.financesDao()
    }

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
                    description = transaction.description.toString(),
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
