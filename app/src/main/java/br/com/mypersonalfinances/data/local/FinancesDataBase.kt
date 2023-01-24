package br.com.mypersonalfinances.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Transaction::class], version = 2)
abstract class FinancesDataBase : RoomDatabase() {
    abstract fun financesDao(): FinancesDAO

    companion object {

        private var instance: FinancesDataBase? = null

        fun getDatabase(context: Context): FinancesDataBase {
            if (instance == null) {
                synchronized(FinancesDataBase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FinancesDataBase::class.java,
                        "transition_database"
                    ).build()
                }
            }
            return instance!!
        }
    }
}