package br.com.mypersonalfinances.model

data class Transaction(
    val id: Int?,
    val amount: Double,
    val description: String,
    val date: String,
    val category: Category,
    val savedMoney: Boolean
)

enum class Category {
    ALIMENTACAO,
    LAZER,
    CASA,
    TRANSPORTE
}
