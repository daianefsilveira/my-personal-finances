package br.com.mypersonalfinances

import android.app.Application
import br.com.mypersonalfinances.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@AppApplication)
            modules(appModules)
        }
    }
}