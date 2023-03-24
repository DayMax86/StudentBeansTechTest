package com.daymax.studentbeanstechtest.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.daymax.studentbeanstechtest.ui.theme.DEFAULT
import com.daymax.studentbeanstechtest.ui.theme.PARAGRAPH
import com.daymax.studentbeanstechtest.ui.theme.SMALL
import com.daymax.studentbeanstechtest.viewmodels.ApiViewModel
import com.daymax.studentbeanstechtest.viewmodels.PhotosViewModel

@Composable
fun PhotosScreen() {

    val viewModel = remember { PhotosViewModel() }

    //I have used the term 'offer' to describe the combination of an image and title from the API
    //  (i.e. the contents of one card.)
    //in the interest of giving the data a less abstract form for this demo.

    PhotosContent(
        viewModel.offers
    )

}

@Composable
fun PhotosContent(
    offersList: MutableList<ApiViewModel.OfferResponse.Offer>
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(),
    ) {

        LazyColumn(
            contentPadding = PaddingValues(all = 10.dp)
        ) {
            items(
                items = offersList,
                itemContent = { offer ->
                    OfferCard(
                        offer.title,
                        offer.thumbnailUrl,
                    )
                }
            )

        }

    }
}

@Composable
fun OfferCard(
    title: String,
    thumbnailUrl: String,
) {

    val minWidth = 100
    val minHeight = 100

    Card(
        modifier = Modifier
            .padding(all = SMALL.dp)
            .defaultMinSize(minHeight = minHeight.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(DEFAULT.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 0.dp,
    ) {

        Row(
            modifier = Modifier
                .fillMaxHeight()
        )
        {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(DEFAULT.dp)),
            ) {
                AsyncImage(
                    modifier = Modifier
                        .clipToBounds()
                        .sizeIn(minWidth = minWidth.dp, minHeight = minHeight.dp)
                        .clip(RoundedCornerShape(DEFAULT.dp)),
                    model = thumbnailUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxHeight(),
            ) {
                Text(
                    modifier = Modifier
                        .padding(DEFAULT.dp),
                    text = title,
                    fontSize = PARAGRAPH.sp,
                    textAlign = TextAlign.Left
                    )
            }

        }

    }

}

