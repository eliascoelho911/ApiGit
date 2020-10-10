@file:Suppress("unused")

package br.com.eliascoelho911

import android.app.Application
import br.com.eliascoelho911.di.repositoryModule
import br.com.eliascoelho911.di.retrofitModule
import br.com.eliascoelho911.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(retrofitModule, repositoryModule, viewModelModule)
        }
    }
}