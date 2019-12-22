package com.monika.kotlinmvvmkoin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.monika.kotlinmvvmkoin.di.apiModule
import com.monika.kotlinmvvmkoin.di.repositoryModule
import com.monika.kotlinmvvmkoin.di.retrofitModule
import com.monika.kotlinmvvmkoin.di.viewModelModule
import com.monika.kotlinmvvmkoin.model.ApiResponse
import com.monika.kotlinmvvmkoin.model.Facts
import com.monika.kotlinmvvmkoin.viewmodel.FactsViewModel
import org.junit.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FactsViewModelTest : KoinTest {

    val viewModel: FactsViewModel by inject ()

    @Mock
    lateinit var uiData: Observer<ApiResponse<Facts>>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin{ modules(listOf(
            repositoryModule,
            viewModelModule,
            retrofitModule,
            apiModule
        ))
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun testGotDetail() {

        // Observe
        viewModel.data.observeForever(uiData)

        // Has received data
        Assert.assertNotNull(viewModel.data.value)

        val factsData = ApiResponse<Facts>()

        // Has been notified
        Mockito.verify(uiData).onChanged(factsData)
    }
}