package com.br.nextpage.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.br.nextpage.R

@Composable
fun Profile(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.home_profile),
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}
