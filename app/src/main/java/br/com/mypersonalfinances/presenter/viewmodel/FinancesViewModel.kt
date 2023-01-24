package br.com.mypersonalfinances.presenter.viewmodel

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import br.com.mypersonalfinances.R
import br.com.mypersonalfinances.data.local.FinancesRepository
import br.com.mypersonalfinances.presenter.HomeCardModel
import br.com.mypersonalfinances.data.local.Transaction
import br.com.mypersonalfinances.data.local.TransactionType
import br.com.mypersonalfinances.presenter.ExtractCardModel
import kotlinx.coroutines.launch

class FinancesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FinancesRepository(application)

    var transactionList = MutableLiveData<List<Transaction>>()

    private var _balance = MutableLiveData<List<HomeCardModel>>()
    var balance: LiveData<List<HomeCardModel>> = _balance

    private var _extract = MutableLiveData<List<ExtractCardModel>>()
    var extract: LiveData<List<ExtractCardModel>> = _extract

    fun updateList() {
        viewModelScope.launch {
            transactionList.value = repository.getAll()

            val transaction = transactionList.value!!

            var incomes = 0.00
            var expenses = 0.00
            var total = 0.0

            transaction.forEach {
                if (it.transactionType == TransactionType.INCOME) {
                    incomes += it.amount
                } else {
                    expenses -= it.amount
                }
            }
            total = incomes + expenses

            val homeCardModelList = listOf(
                HomeCardModel(
                    "Entradas",
                    incomes.toString(),
                    ContextCompat.getDrawable(getApplication(), R.drawable.income),
                    backgroundColor = R.color.soft_green
                ),
                HomeCardModel(
                    "Saídas",
                    expenses.toString(),
                    ContextCompat.getDrawable(getApplication(), R.drawable.expense),
                    backgroundColor = R.color.soft_red
                ),
                HomeCardModel(
                    "Total",
                    total.toString(),
                    ContextCompat.getDrawable(getApplication(), R.drawable.ic_total),
                    backgroundColor = R.color.green
                )
            )

            _balance.value = homeCardModelList

            val extractCardModel = repository.convertTransactionToExtractCardList(transaction)
            _extract.value = extractCardModel

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
