package com.example.spacexproject.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexproject.service.SpaceXRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class  SpacexViewModel @Inject constructor(private val repository:SpaceXRepositoryImpl) :ViewModel() {
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
