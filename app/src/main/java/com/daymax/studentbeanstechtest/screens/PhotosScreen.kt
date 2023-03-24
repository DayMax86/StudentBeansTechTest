package com.daymax.studentbeanstechtest.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.daymax.studentbeanstechtest.viewmodels.ApiViewModel
import com.daymax.studentbeanstechtest.viewmodels.PhotosViewModel
import java.net.URL

@Composable
fun PhotosScreen(navController: NavHostController) {

    val viewModel = remember { PhotosViewModel(navController) }

    PhotosContent(
        viewModel.offers
    )

}

@Composable
fun PhotosContent(
    offersList: MutableList<ApiViewModel.Offer>
) {
    //Replicate example screen here
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
                        offer.imageUrl,
                    )
                }
            )

        }

    }
}

@Composable
fun OfferCard(
    title: String,
    imageUrl: String,
) {

    Card(
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
    ) {



    }

}

