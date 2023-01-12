package br.com.mypersonalfinances.data.local

import android.app.Application
import br.com.mypersonalfinances.model.Balance
import br.com.mypersonalfinances.model.Transaction
import java.math.BigDecimal
import java.math.RoundingMode

class FinancesRepository(application: Application) {

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

    fun totalBalance(transactionList: List<Transaction>): Balance {
        val balance = Balance(0.00, 0.00, 0.00)

        transactionList.forEach{
            if (it.savedMoney) {
                balance.income += it.amount
            } else {
                balance.expense -= it.amount
            }
        }
        balance.income = BigDecimal(balance.income).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        balance.expense = BigDecimal(balance.expense).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        balance.total = BigDecimal(balance.income + balance.expense).setScale(2, RoundingMode.HALF_EVEN).toDouble()

        return balance
    }
}