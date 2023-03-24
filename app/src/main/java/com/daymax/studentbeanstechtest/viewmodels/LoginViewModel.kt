package com.daymax.studentbeanstechtest.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.daymax.studentbeanstechtest.activities.MainActivity.Screens.PHOTOS

class LoginViewModel(
    private val navController: NavHostController
) : ViewModel() {

    var emailInput: String by mutableStateOf("")
    var passwordInput: String by mutableStateOf("")

    fun handleLogin() {
        //This would be a coroutine (viewModelScope) if it were contacting a database for login
        //but there is no need for this demo

        if (emailInput.any { !it.isWhitespace() } && passwordInput.any { !it.isWhitespace() })
        //This check makes sure neither the email or pw is blank, or that it has spaces anywhere
        {
            //Valid login so advance to photos screen
            navController.navigate(PHOTOS)
        }
        else {
            //Email &/ pw must have been blank or contained spaces
            //Feedback invalid login details to user
            invalidLogin()
        }

    }

    private fun invalidLogin() {
        //TODO
    }

    //Update variables on user input so changes are reflected on screen
    fun onEmailInputChange(text: String) {
        emailInput = text
    }
    fun onPasswordInputChange(text: String) {
        passwordInput = text
    }

}