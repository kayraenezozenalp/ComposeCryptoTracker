package com.example.cryptotracker.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.model.CryptoListItem
import com.example.cryptotracker.repository.CryptoRepository
import com.example.cryptotracker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    var cryptoList = mutableStateOf<List<CryptoListItem>>(listOf())
    var errorMessage = mutableStateOf("")
    var isloading = mutableStateOf(false)

    private var initialCryptoList = listOf<CryptoListItem>()
    private var isSearchStarting = true

    init {
        loadCryptos()
    }

    fun searchCryptoList(query: String){

        val listToSearch = if(isSearchStarting){
            cryptoList.value
        }else{
            initialCryptoList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                cryptoList.value = initialCryptoList
                isSearchStarting = true
                return@launch
            }

            val results = listToSearch.filter {
                it.currency.contains(query.trim(), ignoreCase = true)
            }

            if (isSearchStarting){
                initialCryptoList = cryptoList.value
                isSearchStarting = false
            }
            cryptoList.value = results
        }
    }

    fun loadCryptos(){
        viewModelScope.launch {

            isloading.value = true
            val result = repository.getCryptoList()

            when(result){
                is Resource.Success -> {
                        val cryptoItems = result.data!!.mapIndexed { index, cryptoListItem ->
                            CryptoListItem(cryptoListItem.currency,cryptoListItem.price)
                        }

                    errorMessage.value = ""
                    isloading.value = false
                    cryptoList.value += cryptoItems

                }

                is Resource.Loading -> {

                }

                is Resource.Error ->{
                    errorMessage.value = result.message!!
                    isloading.value = false
                }
            }
        }
    }

}