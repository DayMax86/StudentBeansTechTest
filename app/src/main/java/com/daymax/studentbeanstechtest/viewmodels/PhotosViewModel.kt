package com.daymax.studentbeanstechtest.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

class PhotosViewModel(navController: NavHostController) {

    var offers = mutableStateListOf<ApiViewModel.Offer>()
    private val apiViewModel = ApiViewModel()

    init {
        offers = apiViewModel.offers
    }


}