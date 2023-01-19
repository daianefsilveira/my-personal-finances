package br.com.mypersonalfinances.presenter.viewmodel

import android.app.Application
import androidx.lifecycle.*
import br.com.mypersonalfinances.data.local.FinancesRepository
import br.com.mypersonalfinances.presenter.HomeCardModel
import br.com.mypersonalfinances.data.local.Transaction
import kotlinx.coroutines.launch

class FinancesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FinancesRepository(application)

    private var _balance = MutableLiveData<List<HomeCardModel>>()
    var balance : LiveData<List<HomeCardModel>> = _balance

    fun updateList() {
        viewModelScope.launch {
            val transactionList = repository.getAll()
            val homeCardModelList = repository.convertTransactionToHomeCardList(transactionList)
            _balance.value = homeCardModelList
        }
    }

    fun add(transaction: Transaction) {
        viewModelScope.launch {
            repository.insert(transaction)
            updateList()
        }
    }

/*    fun remove(position: Int) {
        viewModelScope.launch {
            val transaction = transactionList.value!![position]
            repository.delete(transaction.id.toString())
            updateList()
        }
    }*/

    class FinancesViewModelFactory(private val application: Application) :
        ViewModelProvider.AndroidViewModelFactory(application) {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =

            FinancesViewModel(application) as T
    }
}
