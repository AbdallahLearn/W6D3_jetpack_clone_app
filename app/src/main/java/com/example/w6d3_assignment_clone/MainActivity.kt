//package com.example.w6d3_assignment_clone
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.pager.HorizontalPager
//import androidx.compose.foundation.pager.rememberPagerState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.w6d3_assignment_clone.navigation.AppNavigation
//
//import com.example.w6d3_assignment_clone.ui.theme.W6D3_Assignment_cloneTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            W6D3_Assignment_cloneTheme {
//                val showOnboarding = remember { mutableStateOf(true) }
//                val navController = rememberNavController()
//
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    if (showOnboarding.value) {
//                        OnboardingScreen(
//                            modifier = Modifier.padding(innerPadding),
//                            navController = navController,
//                            onFinish = { showOnboarding.value = false } // Hide onboarding before navigating
//                        )
//                    } else {
//                        AppNavigation(navController)
//                    }
//                }
//            }
//        }
//
//    }
//}
//
//
//
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun OnboardingScreen(modifier: Modifier = Modifier,  navController: NavController
//                     ,onFinish: () -> Unit) {
//    val pagerState = rememberPagerState { 3 } // 3 onboarding pages
//    val scope = rememberCoroutineScope()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        // Skip Button at the Top Right
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 30.dp),
//            contentAlignment = Alignment.TopEnd
//        ) {
//            TextButton(onClick = { onFinish() }) {
//                Text(text = stringResource(R.string.skip), color = Color.Gray)
//            }
//        }
//
//        // Onboarding Pages (Swipeable)
//        HorizontalPager(
//            state = pagerState,
//            modifier = Modifier.weight(1f) // Takes most of the space
//        ) { page ->
//            OnboardingPage(page, navController, onFinish) // ✅ Correct way to pass navController
//        }
//        // Indicator Dots
//        Row(
//            modifier = Modifier.padding(16.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            repeat(3) { index ->
//                Box(
//                    modifier = Modifier
//                        .size(10.dp)
//                        .padding(4.dp)
//                        .clip(CircleShape)
//                        .background(
//                            if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary
//                            else Color.Gray
//                        )
//                )
//            }
//        }
//
//
//
//    }
//}
//
//@Composable
//fun OnboardingPage(page: Int, navController: NavController, onFinish: () -> Unit) {
//    val titles = listOf(
//        stringResource(R.string.numerous_free_trial_courses),
//        stringResource(R.string.quick_easy_learning),
//        stringResource(R.string.create_study_plan)
//    )
//
//    val descriptions = listOf(
//        stringResource(R.string.free_courses_message),
//        stringResource(R.string.easy_fast_learning),
//        stringResource(R.string.study_plan_message)
//    )
//
//    val images = listOf(
//        R.drawable.male, // Replace with actual drawable resources
//        R.drawable.female,
//        R.drawable.laptop_male
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Image(
//            painter = painterResource(id = images[page]),
//            contentDescription = "Onboarding Image",
//            modifier = Modifier.size(250.dp)
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(
//            text = titles[page],
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            color = MaterialTheme.colorScheme.primary,
//            textAlign = TextAlign.Center
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(
//            text = descriptions[page],
//            fontSize = 16.sp,
//            color = Color.Gray,
//            textAlign = TextAlign.Center
//        )
//
//        if (page == 2) { // Only show buttons on the last page
//            Spacer(modifier = Modifier.height(24.dp))
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                Button(
//                    onClick = {
//                        onFinish()  // Call the onFinish function
//                        navController.navigate("signup") // Navigate to signup
//                    },
//                    modifier = Modifier.weight(1f),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color(0xff4257f5)
//                    )
//                ) {
//                    Text(
//                        text = stringResource(R.string.sign_up),
//                        color = Color.White
//                    )
//                }
//
//                Spacer(modifier = Modifier.width(12.dp))
//
//                OutlinedButton(
//                    onClick = {
//                        onFinish() // Call the onFinish function
//                        navController.navigate("login") // Navigate to login
//                    },
//                    modifier = Modifier.weight(1f),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = ButtonDefaults.outlinedButtonColors(
//                        contentColor = MaterialTheme.colorScheme.onSurface
//                    )
//                ) {
//                    Text(
//                        text = stringResource(R.string.log_in),
//                        color = Color.White
//                    )
//                }
//            }
//        }
//    }
//}
//



package com.example.w6d3_assignment_clone

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.w6d3_assignment_clone.navigation.AppNavigation

import com.example.w6d3_assignment_clone.ui.theme.W6D3_Assignment_cloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            W6D3_Assignment_cloneTheme {
                val showOnboarding = remember { mutableStateOf(true) }
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (showOnboarding.value) {
                        OnboardingScreen(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding), // Apply padding
                            onFinish = { showOnboarding.value = false }
                        )
                    } else {
                        AppNavigation(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding) // Apply padding
                        )
                    }
//                    AppNavigation(
//                        navController = navController,
//                        modifier = Modifier.padding(innerPadding)
//                    )
                }

            }

        }

    }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun OnboardingScreen(navController: NavController, modifier: Modifier = Modifier, onFinish: () -> Unit) {
    val pagerState = rememberPagerState { 3 }
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val isTablet = screenWidth > 600

    Box(modifier = Modifier.fillMaxSize()) {
        // Skip Button at the Top Right
        TextButton(
            onClick = {
                onFinish() // This will navigate after clicking skip
                navController.navigate("login") // Ensure this navigates to "login"
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.skip))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(if (isTablet) 32.dp else 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                OnboardingPage(page, isTablet, navController, onFinish)
            }

            // Indicator Dots
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .size(if (isTablet) 14.dp else 10.dp)
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary else Color.Gray)
                    )
                }
            }
        }
    }
}

@Composable
fun OnboardingPage(page: Int, isTablet: Boolean, navController: NavController, onFinish: () -> Unit) {
    val titles = listOf(
        stringResource(R.string.numerous_free_trial_courses),
        stringResource(R.string.quick_easy_learning),
        stringResource(R.string.create_study_plan)
    )
    val descriptions = listOf(
        stringResource(R.string.free_courses_message),
        stringResource(R.string.easy_fast_learning),
        stringResource(R.string.study_plan_message)
    )
    val images = listOf(
        R.drawable.male, R.drawable.female, R.drawable.laptop_male
    )

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(page) { visible = true }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(500))
        ) {
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = "Onboarding Image",
                modifier = Modifier.size(if (isTablet) 350.dp else 250.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = titles[page],
            fontSize = if (isTablet) 28.sp else 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = descriptions[page],
            fontSize = if (isTablet) 18.sp else 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        if (page == 2) { // Last page
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        onFinish() // Hide onboarding
                        navController.navigate("signup")
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xff4257f5))
                ) {
                    Text(text = stringResource(R.string.sign_up), color = Color.White)
                }
                Spacer(modifier = Modifier.width(12.dp))
                OutlinedButton(
                    onClick = {
                        onFinish() // Hide onboarding
                        navController.navigate("login")
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
                ) {
                    Text(text = stringResource(R.string.log_in), color = Color.White)
                }
            }
        }
    }
}
