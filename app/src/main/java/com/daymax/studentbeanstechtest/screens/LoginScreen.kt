package com.daymax.studentbeanstechtest.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.daymax.studentbeanstechtest.R
import com.daymax.studentbeanstechtest.viewmodels.LoginViewModel

@Composable
fun LoginScreen(navController: NavHostController) {

    val viewModel = remember { LoginViewModel(navController) }

    LoginContent(
        viewModel.emailInput,
        viewModel.passwordInput,
        viewModel::onEmailInputChange,
        viewModel::onPasswordInputChange,
        viewModel::handleLogin
    )
}

@Composable
//Replicate example screen here
fun LoginContent(
    emailInput: String,
    passwordInput: String,
    handleEmailChange: (String) -> Unit,
    handlePasswordChange: (String) -> Unit,
    handleLoginButtonPress: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxHeight(),
    ) {

        Text(
            modifier = Modifier
                .padding(10.dp),
            text = stringResource(R.string.welcome_back)
        )
        Text(
            modifier = Modifier
                .padding(10.dp),
            text = stringResource(R.string.log_in_prompt)
        )

        TextField(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(0.8f),
            value = emailInput,
            onValueChange = handleEmailChange,
        )

        TextField(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(0.8f),
            value = passwordInput,
            onValueChange = handlePasswordChange,
            visualTransformation = PasswordVisualTransformation(),
        )

        Button(
            modifier = Modifier
                .padding(top = 50.dp),
            onClick = { handleLoginButtonPress() },
        ) {
            Text(text = stringResource(id = R.string.log_in))
        }

    }

}


