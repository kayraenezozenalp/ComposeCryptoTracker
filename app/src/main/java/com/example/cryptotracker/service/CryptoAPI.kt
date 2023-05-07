package com.example.cryptotracker.service

import com.example.cryptotracker.model.CryptoList
import com.example.cryptotracker.model.CryptoListItem
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoAPI {

    @GET("/atilsamancioglu/IA32-CryptoComposeData/main/cryptolist.json")
    suspend fun getCryptoList (): CryptoList


}