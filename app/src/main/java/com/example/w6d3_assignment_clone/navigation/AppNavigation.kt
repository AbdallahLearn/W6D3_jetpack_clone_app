package com.example.w6d3_assignment_clone.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.w6d3_assignment_clone.LoginScreen
import com.example.w6d3_assignment_clone.SignupScreen

@Composable
fun AppNavigation(navController: NavHostController ,
                  modifier: Modifier = Modifier) {

    NavHost(
        navController = navController,
        startDestination = "login" // Change to "login" if you want it to start there
    ) {
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignupScreen(navController) }
    }
}