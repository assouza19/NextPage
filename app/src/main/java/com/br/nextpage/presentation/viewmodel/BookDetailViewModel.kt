package com.br.nextpage.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.nextpage.domain.models.BookDetail
import com.br.nextpage.domain.usecase.GetBookDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookDetailViewModel(
    val getBookDetailUseCase: GetBookDetailUseCase
) : ViewModel() {

    private lateinit var _bookDetail: MutableLiveData<BookDetail>

    val bookDetail: LiveData<BookDetail>
        get() = _bookDetail

    fun getBookDetail(idBook: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _bookDetail.value = getBookDetailUseCase(idBook)
            } catch (e: Exception) {
                Log.d("Error", e.toString())
            }
        }
    }
}