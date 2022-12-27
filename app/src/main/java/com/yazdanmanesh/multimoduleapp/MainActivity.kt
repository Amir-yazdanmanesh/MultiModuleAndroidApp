package com.yazdanmanesh.multimoduleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.yazdanmanesh.multimoduleapp.ui.theme.MultiModuleAndroidAppTheme
import com.yazdanmanesh.ui_heroDetail.HeroDetail
import com.yazdanmanesh.ui_heroDetail.Screen
import com.yazdanmanesh.ui_heroList.ui.HeroList
import com.yazdanmanesh.ui_heroList.ui.HeroListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MultiModuleAndroidAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = Screen.HeroList.route,
                    builder = {
                        composable(
                            route = Screen.HeroList.route
                        ) {
                            val viewModel: HeroListViewModel = hiltViewModel()
                            HeroList(
                                state = viewModel.state.value,
                                imageLoader = imageLoader,
                                navigateToDetailScreen ={ heroId ->
                                    navController.navigate(
                                        "${Screen.HeroDetail.route}/$heroId"
                                    )
                                }
                            )
                        }

                        composable(
                            route = Screen.HeroDetail.route+"/heroId",
                            arguments = Screen.HeroDetail.arguments
                        ) { navBackStackEntry ->
                            HeroDetail(
                                heroId = navBackStackEntry.arguments?.getInt("heroId")
                            )
                        }

                    }
                )

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MultiModuleAndroidAppTheme {
        Greeting("Android")
    }
}