package com.daymax.studentbeanstechtest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daymax.studentbeanstechtest.R
import com.daymax.studentbeanstechtest.screens.LoginScreen
import com.daymax.studentbeanstechtest.screens.PhotosScreen
import com.daymax.studentbeanstechtest.ui.BeansTheme

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            var topBarStringResource by remember { mutableStateOf(R.string.app_title) }
            var backArrowEnabled by remember { mutableStateOf(false) } //Should be disabled on login screen

            BeansTheme {

                Scaffold(
                    scaffoldState = rememberScaffoldState(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                ) {
                                    Text(
                                        text = stringResource(id = topBarStringResource),
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        color = MaterialTheme.colors.onBackground,
                                    )
                                }
                            },
                            modifier = Modifier
                                .background(Color.Transparent),

                            navigationIcon = {
                                if (backArrowEnabled) { //Only show back arrow if it's possible to go back

                                    IconButton(
                                        onClick = {
                                            navController.navigateUp()
                                        },
                                    ) {
                                        Icon(
                                            Icons.Filled.ChevronLeft,
                                            contentDescription = "",
                                            tint = MaterialTheme.colors.onBackground
                                        )
                                    }


                                }
                            }
                        )
                    }
                ) { padding ->
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
                                topBarStringResource =
                                    R.string.login_top_bar //Blank string but uses resource reference anyway in case a title is wanted in the future
                                backArrowEnabled = false
                                LoginScreen(navController)
                            }

                            composable(Screens.PHOTOS) {
                                topBarStringResource = R.string.photos
                                backArrowEnabled = true
                                PhotosScreen()
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
