package br.com.mypersonalfinances.viewmodel

import android.app.Application
import androidx.lifecycle.*
import br.com.mypersonalfinances.data.local.FinancesRepository
import br.com.mypersonalfinances.model.Balance
import br.com.mypersonalfinances.model.Transaction
import kotlinx.coroutines.launch

class FinancesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FinancesRepository(application)
    var transactionList = MutableLiveData<List<Transaction>>()
    var balance = MutableLiveData<Balance>()

    fun updateList() {
        viewModelScope.launch {
            transactionList.value = repository.getAll()
            balance.value = repository.totalBalance(transactionList.value!!)
        }
    }

    fun add(transaction: Transaction) {
        viewModelScope.launch {
            repository.insert(transaction)
            updateList()
        }
    }

    fun remove(position: Int) {
        viewModelScope.launch {
            val transaction = transactionList.value!![position]
            repository.delete(transaction.id.toString())
            updateList()
        }
    }

    class FinancesViewModelFactory(private val application: Application) :
        ViewModelProvider.AndroidViewModelFactory(application) {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =

            FinancesViewModel(application) as T
    }
}
