package br.com.mypersonalfinances.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.mypersonalfinances.model.Transaction


@Dao
interface FinancesDAO {
    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("DELETE FROM transaction_table WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT * FROM transaction_table")
    suspend fun getAll(): List<Transaction>
}