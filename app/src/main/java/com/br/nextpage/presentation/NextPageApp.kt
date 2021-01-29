package com.br.nextpage.presentation

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import com.br.nextpage.presentation.bookdetail.BookDetail
import com.br.nextpage.presentation.home.Home
import com.br.nextpage.presentation.theme.JetsnackTheme
import com.br.nextpage.presentation.viewmodel.BookDetailViewModel
import com.br.nextpage.utils.Navigator
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
internal fun NextPageApp(backDispatcher: OnBackPressedDispatcher, viewModel: BookDetailViewModel) {
    val navigator: Navigator<Destination> = rememberSavedInstanceState(
        saver = Navigator.saver<Destination>(backDispatcher)
    ) {
        Navigator(Destination.Home, backDispatcher)
    }
    val actions = remember(navigator) { Actions(navigator) }
    ProvideWindowInsets {
        JetsnackTheme {
            Crossfade(navigator.current) { destination ->
                when (destination) {
                    Destination.Home -> Home(actions.selectBook)
                    is Destination.BookDetail -> BookDetail(
                        bookId = destination.bookId,
                        upPress = actions.upPress
                    )
                }
            }
        }
    }
}
