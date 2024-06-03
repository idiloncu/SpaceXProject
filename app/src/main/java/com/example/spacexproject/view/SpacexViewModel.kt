package com.example.spacexproject.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import javax.inject.Inject

private val <T> Call<T>.isSuccessful: Boolean
    get() {
        TODO("Not yet implemented")
    }

@HiltViewModel
class  SpacexViewModel @Inject constructor() :ViewModel() {
    init {
        fetchRockets()
    }
    private fun fetchRockets() {
        viewModelScope.launch {
           /* val response = repository.getRockets()
            if (response.isSuccessful) {
                val rockets = response.body()
                // Verilerle UI'ı güncelleyin
            } else {
                // Hata durumunu yönetin
            }

            */
        }
    }
}
private fun <T> Call<T>.body(): Any {
    TODO("Not yet implemented")
}
