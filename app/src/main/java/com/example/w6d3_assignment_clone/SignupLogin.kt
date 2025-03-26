package com.example.w6d3_assignment_clone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun AuthScreen(
    title: String,
    buttonText: String,
    navController: NavHostController,
    onSubmit: () -> Unit,
    alternativeText: String,
    onAlternativeClick: () -> Unit,
    showDetailsText: Boolean = false,
    showTermsCheckbox: Boolean = false,
    showForgotPassword: Boolean = false,
    showSocialLogin: Boolean = false
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var termsAccepted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = title, fontSize = 28.sp, fontWeight = FontWeight.Bold)

            if (showDetailsText) {
                Text(text = stringResource(R.string.enter_details), fontSize = 16.sp, color = Color.Gray)
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(R.string.your_email), fontSize = 14.sp, fontWeight = FontWeight.Medium)
            OutlinedTextField(value = email, onValueChange = { email = it }, modifier = Modifier.fillMaxWidth())
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(R.string.password), fontSize = 14.sp, fontWeight = FontWeight.Medium)
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                }
            )
        }

        if (showForgotPassword) {
            TextButton(onClick = { /* Handle forgot password */ }, modifier = Modifier.align(Alignment.End)) {
                Text(text = stringResource(R.string.forgot_password), color = Color.LightGray)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff4257f5))
        ) {
            Text(text = buttonText, color = Color.White, fontSize = 18.sp, modifier = Modifier.padding(10.dp))
        }

        if (showTermsCheckbox) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = termsAccepted, onCheckedChange = { termsAccepted = it })
                Text(text = stringResource(R.string.terms_conditions), fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onAlternativeClick) {
            Text(text = alternativeText)
        }

        if (showSocialLogin) {
            Text(text = stringResource(R.string.or_login_with), fontSize = 16.sp, color = Color.Gray)
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(top = 2.dp)) {
                IconButton(onClick = { /* Handle Google login */ }) {
                    Icon(painter = painterResource(R.drawable.google), contentDescription = "Google", modifier = Modifier.size(38.dp), tint = Color.Unspecified )
                }
                IconButton(onClick = { /* Handle Facebook login */ }) {
                    Icon(painter = painterResource(R.drawable.facebook), contentDescription = "Facebook", modifier = Modifier.size(40.dp), tint = Color.Unspecified )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavHostController) {
    AuthScreen(
        title = stringResource(R.string.login_heading),
        buttonText = stringResource(R.string.login_button),
        navController = navController,
        onSubmit = { /* Handle login logic */ },
        alternativeText = stringResource(R.string.no_account),
        onAlternativeClick = { navController.navigate("signup") },
        showForgotPassword = true,
        showSocialLogin = true
    )
}

@Composable
fun SignupScreen(navController: NavHostController) {
    AuthScreen(
        title = stringResource(R.string.sign_up_heading),
        buttonText = stringResource(R.string.create_account),
        navController = navController,
        onSubmit = { /* Handle signup logic */ },
        alternativeText = stringResource(R.string.already_have_account),
        onAlternativeClick = { navController.navigate("login") },
        showDetailsText = true,
        showTermsCheckbox = true
    )
}
