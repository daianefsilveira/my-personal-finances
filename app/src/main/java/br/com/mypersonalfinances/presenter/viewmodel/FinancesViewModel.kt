package br.com.mypersonalfinances.presenter.viewmodel

import androidx.lifecycle.*
import br.com.mypersonalfinances.data.local.Transaction
import br.com.mypersonalfinances.domain.FinancesUseCase
import br.com.mypersonalfinances.presenter.ExtractCardModel
import br.com.mypersonalfinances.presenter.HomeCardModel
import kotlinx.coroutines.launch

class FinancesViewModel(
    val financesUseCase: FinancesUseCase
) : ViewModel() {

    var transactionList = MutableLiveData<List<Transaction>>()

    private var _balance = MutableLiveData<List<HomeCardModel>>()
    var balance: LiveData<List<HomeCardModel>> = _balance

    private var _extract = MutableLiveData<List<ExtractCardModel>>()
    var extract: LiveData<List<ExtractCardModel>> = _extract

    fun updateList() {
        viewModelScope.launch {
            transactionList.value = financesUseCase.getAll()
            val transaction = transactionList.value!!
            val homeCardList = financesUseCase.createHomeCardList(transaction)
            _balance.value = homeCardList
            val extractCardModel = financesUseCase.convertTransactionToExtractCardList(transaction)
            _extract.value = extractCardModel
        }
    }

    fun add(transaction: Transaction) {
        viewModelScope.launch {
            financesUseCase.insert(transaction)
            updateList()
        }
    }

    fun remove(position: Int) {
        viewModelScope.launch {
            val transaction = transactionList.value!![position]
            financesUseCase.delete(transaction.id.toString())
            updateList()
        }
    }
}
