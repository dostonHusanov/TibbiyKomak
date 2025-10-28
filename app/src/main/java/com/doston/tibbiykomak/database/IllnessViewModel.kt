package com.doston.tibbiykomak.database

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doston.tibbiykomak.data.MainData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class IllnessViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    private val _illnesses = MutableStateFlow<List<MainData>>(emptyList())
    val illnesses: StateFlow<List<MainData>> = _illnesses.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    companion object {
        private const val TAG = "IllnessViewModel"
    }

    init {
        Log.d(TAG, "IllnessViewModel initialized")
    }

    fun loadIllnesses(categoryId: Int) {
        Log.d(TAG, "loadIllnesses called with categoryId: $categoryId")

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            Log.d(TAG, "Loading state set to true")

            try {
                repository.getIllnessesByCategory(categoryId)
                    .catch { exception ->
                        Log.e(TAG, "Error in flow collection", exception)
                        _error.value = exception.message ?: "Unknown error occurred"
                        _isLoading.value = false
                    }
                    .collect { illnessList ->
                        Log.d(TAG, "Received ${illnessList.size} illnesses from repository")
                        _illnesses.value = illnessList
                        _isLoading.value = false

                        if (illnessList.isEmpty()) {
                            Log.w(TAG, "Received empty illness list")
                        }
                    }
            } catch (e: Exception) {
                Log.e(TAG, "Exception in loadIllnesses", e)
                _error.value = e.message ?: "Unknown error occurred"
                _isLoading.value = false
            }
        }
    }

    // New function to load all categories
    fun loadAllCategories() {
        Log.d(TAG, "loadAllCategories called")

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                repository.getAllCategoriesFlow()
                    .catch { exception ->
                        Log.e(TAG, "Error in flow collection", exception)
                        _error.value = exception.message ?: "Unknown error occurred"
                        _isLoading.value = false
                    }
                    .collect { illnessList ->
                        Log.d(TAG, "Received ${illnessList.size} total illnesses from all categories")
                        _illnesses.value = illnessList
                        _isLoading.value = false
                    }
            } catch (e: Exception) {
                Log.e(TAG, "Exception in loadAllCategories", e)
                _error.value = e.message ?: "Unknown error occurred"
                _isLoading.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel cleared")
    }
}