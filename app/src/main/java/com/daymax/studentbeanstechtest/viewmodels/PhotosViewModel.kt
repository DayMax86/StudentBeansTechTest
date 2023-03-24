package com.daymax.studentbeanstechtest.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class PhotosViewModel: ViewModel() {

    var offers = mutableStateListOf<ApiViewModel.OfferResponse.Offer>()
    private val apiViewModel = ApiViewModel()

    init {
        offers = apiViewModel.offers //API fetches data when viewModel is initialised.
    }


}