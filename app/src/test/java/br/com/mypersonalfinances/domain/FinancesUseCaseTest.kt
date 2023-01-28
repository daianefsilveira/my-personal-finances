package br.com.mypersonalfinances.domain

import br.com.mypersonalfinances.R
import br.com.mypersonalfinances.data.local.Category
import br.com.mypersonalfinances.data.local.Transaction
import br.com.mypersonalfinances.data.local.TransactionType
import br.com.mypersonalfinances.presenter.HomeCardModel
import org.junit.Assert.assertEquals
import org.junit.Test

class FinancesUseCaseTest {

    @Test
    fun `WHEN createHomeCards receives list of transactions THEN return list of HomeCardModel`() {
        val transaction = listOf(
            Transaction(
                id = 0,
                amount = 1234.00,
                description = "Test",
                date = "01/01/2020",
                category = Category.CASA,
                transactionType = TransactionType.INCOME
            )
        )

        val realResult = createHomeCards(transaction)

        val mockedResult = listOf(
            HomeCardModel(
                title = "Entradas",
                amount = 1234.00.toString(),
                imagem = R.drawable.income,
                backgroundColor = R.color.soft_green
            ),
            HomeCardModel(
                title = "Saídas",
                amount = 0.0.toString(),
                imagem =  R.drawable.expense,
                backgroundColor = R.color.soft_red
            ),
            HomeCardModel(
                title = "Total",
                amount = 1234.00.toString(),
                imagem =  R.drawable.ic_total,
                backgroundColor = R.color.green
            )
        )
        assertEquals(realResult, mockedResult)
    }
}