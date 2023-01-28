package br.com.mypersonalfinances.domain

import br.com.mypersonalfinances.R
import br.com.mypersonalfinances.data.local.FinancesRepository
import br.com.mypersonalfinances.data.local.Transaction
import br.com.mypersonalfinances.data.local.TransactionType
import br.com.mypersonalfinances.presenter.ExtractCardModel
import br.com.mypersonalfinances.presenter.HomeCardModel

class FinancesUseCase(
    val repository: FinancesRepository
) {
    suspend fun getAll(): List<Transaction>? {
        return repository.getAll()
    }

    fun convertTransactionToExtractCardList(transaction: List<Transaction>): List<ExtractCardModel>? {
        return repository.convertTransactionToExtractCardList(transaction)
    }

    suspend fun insert(transaction: Transaction) {
        repository.insert(transaction)
    }

    suspend fun delete(id: String) {
        repository.delete(id)
    }
}

fun createHomeCards(transaction: List<Transaction>): List<HomeCardModel> {
    var incomes = 0.0
    var expenses = 0.0
    var total = 0.0
    transaction.forEach {
        if (it.transactionType == TransactionType.INCOME) {
            incomes += it.amount
        } else {
            expenses -= it.amount
        }
    }
    total = incomes + expenses
    val homeCardModelList = createHomeCardList(incomes, expenses, total)
    return homeCardModelList
}

fun createHomeCardList(
    incomes: Double,
    expenses: Double,
    total: Double
): List<HomeCardModel> {
    return listOf(
        HomeCardModel(
            "Entradas",
            incomes.toString(),
            R.drawable.income,
            backgroundColor = R.color.soft_green
        ),
        HomeCardModel(
            "Saídas",
            expenses.toString(),
            R.drawable.expense,
            backgroundColor = R.color.soft_red
        ),
        HomeCardModel(
            "Total",
            total.toString(),
            R.drawable.ic_total,
            backgroundColor = R.color.green
        )
    )
}
