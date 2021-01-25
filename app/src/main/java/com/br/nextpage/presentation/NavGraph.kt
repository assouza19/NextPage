package com.br.nextpage.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.br.nextpage.utils.Navigator
import kotlinx.parcelize.Parcelize

sealed class Destination : Parcelable {
    @Parcelize
    object Home : Destination()

    @Immutable
    @Parcelize
    data class BookDetail(val bookId: Long) : Destination()
}

class Actions(navigator: Navigator<Destination>) {
    val selectBook: (Long) -> Unit = { bookId: Long ->
        navigator.navigate(Destination.BookDetail(bookId))
    }
    val upPress: () -> Unit = {
        navigator.back()
    }
}
