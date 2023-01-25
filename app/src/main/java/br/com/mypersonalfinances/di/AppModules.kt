package br.com.mypersonalfinances.di

import br.com.mypersonalfinances.data.local.FinancesDataBase
import br.com.mypersonalfinances.data.local.FinancesRepository
import br.com.mypersonalfinances.domain.FinancesUseCase
import br.com.mypersonalfinances.presenter.viewmodel.FinancesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel {
        FinancesViewModel(
            financesUseCase = get()
        )
    }

    factory {
        FinancesRepository(
            financesDAO = get()
        )
    }

    single {
        FinancesDataBase.getDatabase(androidContext()).financesDao()
    }

    factory {
        FinancesUseCase(
            repository = get()
        )
    }
}