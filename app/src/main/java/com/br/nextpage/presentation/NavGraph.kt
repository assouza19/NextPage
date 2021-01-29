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
    data class BookDetail(val bookId: String) : Destination()
}

class Actions(navigator: Navigator<Destination>) {
    val selectBook: (String) -> Unit = { bookId: String ->
        navigator.navigate(Destination.BookDetail(bookId))
    }
    val upPress: () -> Unit = {
        navigator.back()
    }
}
