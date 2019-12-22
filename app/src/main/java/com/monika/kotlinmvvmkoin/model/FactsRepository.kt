package com.monika.kotlinmvvmkoin.model

import com.monika.kotlinmvvmkoin.network.ApiInterface

class FactsRepository(private val api: ApiInterface) {
    fun getAllFacts() = api.getFacts()
}