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

    var validLogin: Boolean by mutableStateOf(true)

    fun handleLogin() {

        if (emailInput.any { !it.isWhitespace() } && passwordInput.any { !it.isWhitespace() })
        //This check makes sure neither the email or pw is blank, or that it has spaces anywhere
        {
            //Valid login so advance to photos screen
            navController.navigate(PHOTOS)
        } else {
            //Email &/ pw must have been blank or contained spaces
            //Feedback to user
            invalidLogin()
        }
    }

    private fun invalidLogin() { //Extracted to separate method in case invalid login behaviour needs changing in the future
        validLogin = false
    }

    //Update variables on user input so changes are reflected on screen
    fun onEmailInputChange(text: String) {
        emailInput = text
        validLogin = true
    }
    fun onPasswordInputChange(text: String) {
        passwordInput = text
        validLogin = true
    }

}