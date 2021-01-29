package com.br.nextpage.presentation.bookdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.br.nextpage.domain.models.BookButton
import com.br.nextpage.domain.models.BookDetail
import com.br.nextpage.presentation.theme.JetsnackTheme
import com.br.nextpage.presentation.theme.Neutral8
import com.br.nextpage.presentation.viewmodel.BookDetailViewModel
import com.example.jetsnack.ui.components.*
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import kotlin.math.max
import kotlin.math.min

private val BottomBarHeight = 56.dp
private val TitleHeight = 128.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun BookDetail(
    bookId: String,
    viewModel: BookDetailViewModel,
    upPress: () -> Unit
) {
    val book = remember(bookId) {
        viewModel.getBookDetail(bookId)
    }
//    val related = remember(bookId) { SnackRepo.getRelated(bookId) }

    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState(0f)
        Header()
//        Body(related, scroll)
        Title(book, scroll.value)
        Image(book.cover.orEmpty(), scroll.value)
        Up(upPress)
        CartBottomBar(modifier = Modifier.align(Alignment.BottomCenter), book.buttons)
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier = Modifier
            .preferredHeight(280.dp)
            .fillMaxWidth()
            .background(Brush.horizontalGradient(JetsnackTheme.colors.interactivePrimary))
    )
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .preferredSize(36.dp)
            .background(
                color = Neutral8.copy(alpha = 0.32f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            tint = JetsnackTheme.colors.iconInteractive
        )
    }
}

//@Composable
//private fun Body(
//    related: List<SnackCollection>,
//    scroll: ScrollState
//) {
//    Column {
//        Spacer(
//            modifier = Modifier
//                .fillMaxWidth()
//                .statusBarsPadding()
//                .preferredHeight(MinTitleOffset)
//        )
//        ScrollableColumn(scrollState = scroll) {
//            Spacer(Modifier.preferredHeight(GradientScroll))
//            JetsnackSurface(Modifier.fillMaxWidth()) {
//                Column {
//                    Spacer(Modifier.preferredHeight(ImageOverlap))
//                    Spacer(Modifier.preferredHeight(TitleHeight))
//
//                    Spacer(Modifier.preferredHeight(16.dp))
//                    Text(
//                        text = stringResource(R.string.detail_header),
//                        style = MaterialTheme.typography.overline,
//                        color = JetsnackTheme.colors.textHelp,
//                        modifier = HzPadding
//                    )
//                    Spacer(Modifier.preferredHeight(4.dp))
//                    Text(
//                        text = stringResource(R.string.detail_placeholder),
//                        style = MaterialTheme.typography.body1,
//                        color = JetsnackTheme.colors.textHelp,
//                        modifier = HzPadding
//                    )
//
//                    Spacer(Modifier.preferredHeight(40.dp))
//                    Text(
//                        text = stringResource(R.string.ingredients),
//                        style = MaterialTheme.typography.overline,
//                        color = JetsnackTheme.colors.textHelp,
//                        modifier = HzPadding
//                    )
//                    Spacer(Modifier.preferredHeight(4.dp))
//                    Text(
//                        text = stringResource(R.string.ingredients_list),
//                        style = MaterialTheme.typography.body1,
//                        color = JetsnackTheme.colors.textHelp,
//                        modifier = HzPadding
//                    )
//
//                    Spacer(Modifier.preferredHeight(16.dp))
//                    JetsnackDivider()
//
//                    related.forEach { snackCollection ->
//                        key(snackCollection.id) {
//                            SnackCollection(
//                                snackCollection = snackCollection,
//                                onSnackClick = { },
//                                highlight = false
//                            )
//                        }
//                    }
//
//                    Spacer(
//                        modifier = Modifier
//                            .padding(bottom = BottomBarHeight)
//                            .navigationBarsPadding(left = false, right = false)
//                            .preferredHeight(8.dp)
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
private fun Title(book: BookDetail, scroll: Float) {
    val maxOffset = with(AmbientDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(AmbientDensity.current) { MinTitleOffset.toPx() }
    val offset = (maxOffset - scroll).coerceAtLeast(minOffset)
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .preferredHeightIn(min = TitleHeight)
            .statusBarsPadding()
            .graphicsLayer { translationY = offset }
            .background(color = JetsnackTheme.colors.uiBackground)
    ) {
        Spacer(Modifier.preferredHeight(16.dp))
        Text(
            text = book.title.orEmpty(),
            style = MaterialTheme.typography.h4,
            color = JetsnackTheme.colors.textSecondary,
            modifier = HzPadding
        )
        Text(
            text = book.author.orEmpty(),
            style = MaterialTheme.typography.subtitle2,
            fontSize = 20.sp,
            color = JetsnackTheme.colors.textHelp,
            modifier = HzPadding
        )
        Spacer(Modifier.preferredHeight(4.dp))
        Text(
            text = book.author.orEmpty(),
            style = MaterialTheme.typography.h6,
            color = JetsnackTheme.colors.textPrimary,
            modifier = HzPadding
        )

        Spacer(Modifier.preferredHeight(8.dp))
        JetsnackDivider()
    }
}

@Composable
private fun Image(
    imageUrl: String,
    scroll: Float
) {
    val collapseRange = with(AmbientDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFraction = (scroll / collapseRange).coerceIn(0f, 1f)

    CollapsingImageLayout(
        collapseFraction = collapseFraction,
        modifier = HzPadding.then(Modifier.statusBarsPadding())
    ) {
        SnackImage(
            imageUrl = imageUrl,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CollapsingImageLayout(
    collapseFraction: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val imageMaxSize = min(ExpandedImageSize.toIntPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.toIntPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).toIntPx()
        val imageX = lerp(
            (constraints.maxWidth - imageWidth) / 2, // centered when expanded
            constraints.maxWidth - imageWidth, // right aligned when collapsed
            collapseFraction
        )
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.place(imageX, imageY)
        }
    }
}

@Composable
private fun CartBottomBar(modifier: Modifier = Modifier, buttons: List<BookButton>?) {
    val (count, updateCount) = remember { mutableStateOf(1) }
    JetsnackSurface(modifier) {
        Column {
            JetsnackDivider()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .navigationBarsPadding(left = false, right = false)
                    .then(HzPadding)
                    .preferredHeightIn(min = BottomBarHeight)
            ) {
                QuantitySelector(
                    count = count,
                    decreaseItemCount = { if (count > 0) updateCount(count - 1) },
                    increaseItemCount = { updateCount(count + 1) }
                )
                Spacer(Modifier.preferredWidth(16.dp))
                JetsnackButton(
                    onClick = { /* todo */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = buttons?.get(0)?.description.orEmpty(),
                        maxLines = 1
                    )
                }
            }
        }
    }
}