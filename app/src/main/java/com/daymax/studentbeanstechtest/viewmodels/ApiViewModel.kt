package com.daymax.studentbeanstechtest.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ApiViewModel : ViewModel() {

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    var offers = mutableStateListOf<OfferResponse.Offer>()
    //An 'offer' is each object in the JSON response

    interface OffersApi {
        @GET("photos")
        suspend fun getOffers(): OfferResponse
    }

    private val offersService: OffersApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OffersApi::class.java)

    init {
        offersService.apply { //Make sure the offersService has been instantiated before calling the getOffers method
            fetchOffers()
        }
    }

    private fun fetchOffers(
    ) {
        viewModelScope
            .launch {
                try {
                    val tempList: OfferResponse = offersService.getOffers()
                    offers.addAll(tempList)
                } catch (e: Exception) {
                    Log.e("Api", "Api error-> + $e")
                }
            }
    }

    class OfferResponse : ArrayList<OfferResponse.Offer>() { //Generated with JsonToKotlinClass plugin
        data class Offer(
            val albumId: Int,
            val id: Int,
            val thumbnailUrl: String,
            val title: String,
            val url: String
        )

    }

}
