package com.example.instaclon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instaclon.presentation.Authentication.AuthenticationViewModel
import com.example.instaclon.presentation.Authentication.LoginScreen
import com.example.instaclon.presentation.Authentication.SignUpScreen
import com.example.instaclon.presentation.Main.FeedsScreen
import com.example.instaclon.presentation.Main.Profile.ProfileScreen
import com.example.instaclon.presentation.Main.SearchScreen
import com.example.instaclon.presentation.SplashScreen
import com.example.instaclon.ui.theme.InstaclonTheme
import com.example.instaclon.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstaclonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    val navController= rememberNavController()
                    val authViewModel : AuthenticationViewModel= hiltViewModel()
                    InstagramCloneApp(navController, authenticationViewModel = authViewModel)
                }
            }
        }
    }
}
@Composable
fun InstagramCloneApp(navController: NavHostController,authenticationViewModel: AuthenticationViewModel){
    NavHost(navController =navController,startDestination= Screens.SplashScreen.route){
        composable(route=Screens.LoginScreen.route){
            LoginScreen(navController= navController,authenticationViewModel)
        }
        composable(route=Screens.SignUpScreen.route){
            SignUpScreen(navController,authenticationViewModel)
        }
        composable(route=Screens.SplashScreen.route){
            SplashScreen(navController = navController, authViewModel = authenticationViewModel)
        }
        composable(route=Screens.FeedsScreen.route){
            FeedsScreen(navController=navController)
        }
        composable(route=Screens.ProfileScreen.route){
            ProfileScreen(navController=navController)
        }
        composable(route=Screens.SearchScreen.route){
            SearchScreen(navController=navController)
        }
    }
}

