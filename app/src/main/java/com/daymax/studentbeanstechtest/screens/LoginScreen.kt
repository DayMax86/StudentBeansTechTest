package com.daymax.studentbeanstechtest.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.daymax.studentbeanstechtest.R
import com.daymax.studentbeanstechtest.ui.theme.*
import com.daymax.studentbeanstechtest.viewmodels.LoginViewModel

@Composable
fun LoginScreen(navController: NavHostController) {

    val viewModel = remember { LoginViewModel(navController) }

    LoginContent(
        viewModel.emailInput,
        viewModel.passwordInput,
        viewModel::onEmailInputChange,
        viewModel::onPasswordInputChange,
        viewModel::handleLogin,
        viewModel.validLogin,
    )
}

@Composable
//Constants used for padding and font values are defined in ui/theme
fun LoginContent(
    emailInput: String,
    passwordInput: String,
    handleEmailChange: (String) -> Unit,
    handlePasswordChange: (String) -> Unit,
    handleLoginButtonPress: () -> Unit,
    validLogin: Boolean,
) {

    Box( //Place content in Box for centre alignment
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(),
        ) {

            Text(
                modifier = Modifier
                    .padding(start = DEFAULT.dp, bottom = SMALL.dp),
                text = stringResource(R.string.welcome_back),
                fontSize = HEADING.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier
                    .padding(start = DEFAULT.dp, top = SMALL.dp, bottom = LARGE.dp),
                text = stringResource(R.string.log_in_prompt)
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(DEFAULT.dp)
                    .fillMaxWidth(),
                value = emailInput,
                onValueChange = handleEmailChange,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.email_placeholder),
                        color = MaterialTheme.colors.secondaryVariant
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = if (validLogin) {
                        MaterialTheme.colors.primary
                    } else {
                        MaterialTheme.colors.onError
                    },
                    unfocusedBorderColor = if (validLogin) {
                        Transparent
                    } else {
                        MaterialTheme.colors.onError
                    },
                    backgroundColor = MaterialTheme.colors.secondary,
                )
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(DEFAULT.dp)
                    .fillMaxWidth(),
                value = passwordInput,
                onValueChange = handlePasswordChange,
                visualTransformation = PasswordVisualTransformation(),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.password_placeholder),
                        color = MaterialTheme.colors.secondaryVariant
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = if (validLogin) {
                        MaterialTheme.colors.primary
                    } else {
                        MaterialTheme.colors.onError
                    },
                    unfocusedBorderColor = if (validLogin) {
                        Transparent
                    } else {
                        MaterialTheme.colors.onError
                    },
                    backgroundColor = MaterialTheme.colors.secondary,
                )
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = DEFAULT.dp, end = DEFAULT.dp, bottom = DEFAULT.dp, top = EXTRA_LARGE.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                onClick = { handleLoginButtonPress() },
            ) {
                Text(
                    modifier = Modifier
                        .padding(all = SMALL.dp),
                    text = stringResource(id = R.string.log_in)
                )
            }

        }
    }

}


