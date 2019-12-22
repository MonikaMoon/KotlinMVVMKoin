package com.monika.kotlinmvvmkoin

import com.monika.kotlinmvvmkoin.di.apiModule
import com.monika.kotlinmvvmkoin.di.repositoryModule
import com.monika.kotlinmvvmkoin.di.retrofitModule
import com.monika.kotlinmvvmkoin.di.viewModelModule
import com.monika.kotlinmvvmkoin.viewmodel.FactsViewModel
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.get
import org.koin.test.inject

class KoinModuleTest : KoinTest {

    @Test
    fun myTest() {
        // You can start your Koin configuration
        startKoin { modules(listOf(
            repositoryModule,
            viewModelModule,
            retrofitModule,
            apiModule
        ))
        }
    }
}