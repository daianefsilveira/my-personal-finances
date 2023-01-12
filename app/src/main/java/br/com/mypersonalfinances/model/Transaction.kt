package br.com.mypersonalfinances.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val amount: Double,
    val description: String,
    val date: String,
    val category: Category,
    val savedMoney: Boolean
)

enum class Category {
    VIAGEM,
    SAUDE,
    CASA,
    ALIMENTACAO,
    MERCADO,
    TRANSPORTE,
    LAZER,
    SALARIO,
    BONIFICACAO,
    ECONOMIZEI,
    OUTROS

}
