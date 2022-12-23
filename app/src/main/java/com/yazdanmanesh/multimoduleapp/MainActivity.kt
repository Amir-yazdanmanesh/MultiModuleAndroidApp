package com.yazdanmanesh.multimoduleapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.yazdanmanesh.core.DataState
import com.yazdanmanesh.core.Logger
import com.yazdanmanesh.core.ProgressBarState
import com.yazdanmanesh.core.UIComponent
import com.yazdanmanesh.hero_interactors.HeroInteractions
import com.yazdanmanesh.multimoduleapp.ui.theme.MultiModuleAndroidAppTheme
import com.yazdanmanesh.ui_heroList.ui.HeroList
import com.yazdanmanesh.ui_heroList.ui.HeroListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import coil.ImageLoader
import com.yazdanmanesh.ui_heroList.ui.HeroListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var imageLoader:ImageLoader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageLoader = ImageLoader.Builder(applicationContext)
            .error(R.drawable.error_image)
            .placeholder(R.drawable.white_background)
            .availableMemoryPercentage(0.25)
            .crossfade(true)
            .build()

        setContent {
            MultiModuleAndroidAppTheme {
                val viewModel:HeroListViewModel = hiltViewModel()

                HeroList(
                    state = viewModel.state.value,
                    imageLoader = imageLoader
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