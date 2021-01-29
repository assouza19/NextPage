package com.br.nextpage.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.nextpage.presentation.theme.JetsnackTheme
import com.example.jetsnack.ui.components.SnackCollection
import dev.chrisbanes.accompanist.insets.statusBarsHeight

@Composable
fun Feed(
    onSnackClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val snackCollections = remember { SnackRepo.getSnacks() }
    val filters = remember { SnackRepo.getFilters() }
    Feed(
        snackCollections,
        filters,
        onSnackClick,
        modifier
    )
}

@Composable
private fun Feed(
    snackCollections: List<SnackCollection>,
    filters: List<Filter>,
    onSnackClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    JetsnackSurface(modifier = modifier.fillMaxSize()) {
        Box {
            SnackCollectionList(snackCollections, filters, onSnackClick)
            DestinationBar()
        }
    }
}

@Composable
private fun SnackCollectionList(
    snackCollections: List<SnackCollection>,
    filters: List<Filter>,
    onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Spacer(Modifier.statusBarsHeight(additional = 56.dp))
            FilterBar(filters)
        }
        itemsIndexed(snackCollections) { index, snackCollection ->
            if (index > 0) {
                JetsnackDivider(thickness = 2.dp)
            }
            SnackCollection(
                snackCollection = snackCollection,
                onSnackClick = onSnackClick,
                index = index
            )
        }
    }
}

@Preview("Home")
@Composable
fun HomePreview() {
    JetsnackTheme {
        Feed(onSnackClick = { })
    }
}

@Preview("Home • Dark Theme")
@Composable
fun HomeDarkPreview() {
    JetsnackTheme(darkTheme = true) {
        Feed(onSnackClick = { })
    }
}
