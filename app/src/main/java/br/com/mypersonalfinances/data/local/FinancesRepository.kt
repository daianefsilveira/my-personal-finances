package br.com.mypersonalfinances.data.local

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import br.com.mypersonalfinances.R
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

    fun convertTransactionToHomeCardList(transactionList: List<Transaction>): List<HomeCardModel> {
        val homeCardModelList = mutableListOf<HomeCardModel>()

        transactionList.forEach { transaction ->
            homeCardModelList.add(
                HomeCardModel(
                    amount = transaction.amount.toString(),
                    title = getTitle(transaction),
                    imagem = getImage(transaction),
                    backgroundColor = getBackground(transaction)
                )
            )
        }
        return homeCardModelList
    }

    private fun getBackground(transaction: Transaction): Int {
        return when (transaction.transactionType) {
            TransactionType.INCOME -> R.color.soft_green
            TransactionType.EXPENSE -> R.color.soft_red
            TransactionType.TOTAL -> R.color.green
        }
    }

    private fun getImage(transaction: Transaction): Drawable {
        return when (transaction.transactionType) {
            TransactionType.INCOME -> ContextCompat.getDrawable(application, R.drawable.income)!!
            TransactionType.EXPENSE -> ContextCompat.getDrawable(application, R.drawable.expense)!!
            TransactionType.TOTAL -> ContextCompat.getDrawable(application, R.drawable.ic_total)!!
        }
    }

    private fun getTitle(transaction: Transaction): String {
        return when (transaction.transactionType) {
            TransactionType.INCOME -> "Entradas"
            TransactionType.EXPENSE -> "Saídas"
            TransactionType.TOTAL -> "Total"
        }
    }
}
