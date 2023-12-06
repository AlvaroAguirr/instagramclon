package com.example.instaclon.presentation.Main.Profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.instaclon.presentation.BottomNavegationitem
import com.example.instaclon.presentation.BottomNavigationMenu
import com.example.instaclon.util.Response
import com.example.instaclon.R
import com.example.instaclon.domain.model.TabModel
import com.example.instaclon.presentation.Main.Profile.components.ActionButton
import com.example.instaclon.presentation.Main.Profile.components.MyProfile
import com.example.instaclon.presentation.Main.Profile.components.PostSection
import com.example.instaclon.presentation.Main.Profile.components.ProfileStats
import com.example.instaclon.presentation.Main.Profile.components.RoundedImage
import com.example.instaclon.presentation.Main.Profile.components.TabView
import com.example.instaclon.presentation.Main.UserViewModel
import com.example.instaclon.presentation.Toast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val userviewmodel: UserViewModel = hiltViewModel()
    userviewmodel.getUserInfo()
    when (val response = userviewmodel.getUserData.value) {
        is Response.Error ->{
        Toast(message = response.message)
        }
        is Response.Loading -> {
            CircularProgressIndicator()
        }

        is Response.Success -> {
            if (response.data != null) {
                val obj = response.data
                var selectedTabIndex by remember { mutableStateOf(0) }
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(modifier = Modifier.weight(1f)) {

                        TopAppBar(
                            title = {
                                Text(
                                    text = obj.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    fontStyle = FontStyle.Italic
                                )
                            },
                            actions = {
                                Icon(
                                    painter = painterResource(id = R.drawable.nahida_de_echo),
                                    contentDescription = "Create",
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Icon(
                                    painter = painterResource(id = R.drawable.descargar),
                                    contentDescription = "more Opcionts",
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                        )


                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 10.dp, start = 10.dp, bottom = 10.dp, end = 20.dp
                                    )
                            ) {

                                RoundedImage(
                                    image = rememberImagePainter(data = obj.imageUrl),
                                    modifier = Modifier
                                        .size(100.dp)
                                        .weight(3.3f)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    modifier = Modifier.weight(6.5f)
                                ) {
                                    ProfileStats(
                                        numberText = "133",
                                        text = "Post",
                                        navController = navController
                                    )
                                    ProfileStats(
                                        numberText = "123",
                                        text = "Followers",
                                        navController = navController
                                    )
                                    ProfileStats(
                                        numberText = obj.following.size.toString(),
                                        text = "Following",
                                        navController = navController
                                    )
                                }
                            }
                        }
                        MyProfile(
                            displayName = obj.name,
                            bio = obj.bio,
                            url = obj.url
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(horizontal = 20.dp)
                        ) {


                            ActionButton(
                                text = "Edit profile",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.45f)
                                    .height(35.dp)
                                    .clickable {
                                    }
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        TabView(
                            tabModels = listOf(
                                TabModel(
                                    image = painterResource(id = R.drawable.descargar),
                                    text = "Post"
                                ),
                                TabModel(
                                    image = painterResource(id = R.drawable.descargar),
                                    text = "reels"
                                ),
                                TabModel(
                                    image = painterResource(id = R.drawable.descargar),
                                    text = "igtv"
                                )

                            )
                        ) {
                            selectedTabIndex = it
                        }
                        when (selectedTabIndex) {
                            0 -> {
                                PostSection(
                                    posts = listOf(
                                        painterResource(id = R.drawable.bugsnag_full_color),
                                        painterResource(id = R.drawable.bugsnag_full_color),
                                        painterResource(id = R.drawable.bugsnag_full_color),
                                        painterResource(id = R.drawable.bugsnag_full_color)

                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp)
                                )
                            }

                            1 -> {

                            }

                            2 -> {

                            }
                        }
                    }
                    BottomNavigationMenu(
                        selectItem = BottomNavegationitem.PROFILE,
                        navController = navController
                    )

                }

            }


        }
        else -> {}
    }
}

