package com.monika.kotlinmvvmkoin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monika.kotlinmvvmkoin.model.ApiResponse
import com.monika.kotlinmvvmkoin.model.Facts
import com.monika.kotlinmvvmkoin.model.FactsRepository
import com.monika.kotlinmvvmkoin.utils.LoadingState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FactsViewModel(private val factsRepository: FactsRepository) : ViewModel(),
    Callback<ApiResponse<Facts>> {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _data = MutableLiveData<ApiResponse<Facts>>()
    val data: LiveData<ApiResponse<Facts>>
        get() = _data

    init {
        fetchData()
    }

    private fun fetchData() {
        _loadingState.postValue(LoadingState.LOADING)
        factsRepository.getAllFacts().enqueue(this)
    }

    override fun onFailure(call: Call<ApiResponse<Facts>>, t: Throwable) {
        _loadingState.postValue(LoadingState.error(t.message))
    }

    override fun onResponse(call: Call<ApiResponse<Facts>>, response: Response<ApiResponse<Facts>>) {
        if (response.isSuccessful) {
            _data.postValue(response.body())
            _loadingState.postValue(LoadingState.LOADED)
        } else {
            _loadingState.postValue(LoadingState.error(response.errorBody().toString()))
        }
    }
}