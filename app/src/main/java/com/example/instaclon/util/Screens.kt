package com.example.instaclon.util

sealed class Screens(val route:String ){
    object SplashScreen:Screens("splash_scren")
    object LoginScreen:Screens("Loginh_screen")
    object SignUpScreen: Screens("signup_screen")
    object FeedsScreen: Screens("feed_screen")
    object ProfileScreen :Screens("profile_screen")
    object SearchScreen :Screens("search_Screen")
}
