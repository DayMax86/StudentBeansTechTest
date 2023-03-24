package com.daymax.studentbeanstechtest.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ApiViewModel : ViewModel() {

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    var offers = mutableStateListOf<Offer>()

    init {
        fetchOffers(10) //Get the first 10 results for now
    }

    interface OffersApi {
        @GET("photos")
        suspend fun getOffers(): OfferResponse
    }

    private val offersService: OffersApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OffersApi::class.java)

    data class OfferResponse(
        val offers: List<Offer>
    )
    private fun OfferResponse.toModel(index: Int) =
        Offer(
            title = offers[index].title,
            imageUrl = offers[index].imageUrl,
        )

    data class Offer(
        val title: String,
        val imageUrl: String,
    )

    fun fetchOffers(
        count: Int
    ) {
        viewModelScope
            .launch {
                try {
                    val tempList: OfferResponse = offersService.getOffers()
                    for (i in 0 until count) {
                        offers.add(tempList.toModel(i))
                    }
                } catch (e: Exception) {
                    Log.e("Api", "$e")
                }
            }
    }


}