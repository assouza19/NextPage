package com.br.nextpage.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.setContent
import androidx.core.view.WindowCompat
import com.br.nextpage.R
import com.br.nextpage.presentation.viewmodel.BookDetailViewModel
import com.br.nextpage.utils.SysUiController
import com.br.nextpage.utils.SystemUiController
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel : BookDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = remember { SystemUiController(window) }
            Providers(SysUiController provides systemUiController) {
                NextPageApp(onBackPressedDispatcher, viewModel = viewModel)
            }
        }
    }
}