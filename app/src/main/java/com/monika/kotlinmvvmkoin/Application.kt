package com.monika.kotlinmvvmkoin

import com.monika.kotlinmvvmkoin.di.apiModule
import com.monika.kotlinmvvmkoin.di.repositoryModule
import com.monika.kotlinmvvmkoin.di.retrofitModule
import com.monika.kotlinmvvmkoin.di.viewModelModule
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this) //init realmdb this covers all use of realm within the project.

        //start Koin
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@Application)
            modules(listOf(
                repositoryModule,
                viewModelModule,
                retrofitModule,
                apiModule
            ))
        }
    }
}