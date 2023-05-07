package com.example.cryptotracker.repository

import com.example.cryptotracker.model.CryptoList
import com.example.cryptotracker.service.CryptoAPI
import com.example.cryptotracker.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api : CryptoAPI
){
    suspend fun getCryptoList() : Resource<CryptoList> {
        val response = try {
                api.getCryptoList()
        }catch (e: Exception){
            return Resource.Error("Error")
        }
        return Resource.Success(response)
    }
}