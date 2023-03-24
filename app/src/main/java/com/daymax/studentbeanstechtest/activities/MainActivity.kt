package com.daymax.studentbeanstechtest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daymax.studentbeanstechtest.R
import com.daymax.studentbeanstechtest.screens.LoginScreen
import com.daymax.studentbeanstechtest.screens.PhotosScreen
import com.daymax.studentbeanstechtest.ui.BeansTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            //Create navController
            val navController = rememberNavController()

            //Create viewModel
            //val viewModel = remember { MainViewModel(navController) }

            var topBarStringResource by remember { mutableStateOf(R.string.app_title) }

            BeansTheme {
                //Add scaffold for the topAppBar text
                Scaffold(
                    scaffoldState = rememberScaffoldState(),
                    topBar = {
                        title = {
                            topBarStringResource
                        }.toString()
                    }
                ) { padding ->
                    //Add surface and populate NavGraph
                    Surface(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxHeight(),
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screens.LOGIN,
                        )
                        {

                            composable(Screens.LOGIN) {
                                topBarStringResource = R.string.login_top_bar //Blank string but uses resource reference anyway in case a title is wanted in the future
                                LoginScreen(navController)
                            }

                            composable(Screens.PHOTOS) {
                                topBarStringResource = R.string.photos
                                PhotosScreen(navController)
                            }

                        }
                    }
                }
            }





        }

    }

    object Screens {
        const val LOGIN = "login"
        const val PHOTOS = "photos"
    }

}