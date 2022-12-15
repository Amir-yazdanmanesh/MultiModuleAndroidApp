package com.yazdanmanesh.multimoduleapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.yazdanmanesh.core.DataState
import com.yazdanmanesh.core.Logger
import com.yazdanmanesh.core.ProgressBarState
import com.yazdanmanesh.core.UIComponent
import com.yazdanmanesh.hero_domain.Hero
import com.yazdanmanesh.hero_interactors.HeroInteractions
import com.yazdanmanesh.multimoduleapp.ui.theme.MultiModuleAndroidAppTheme
import com.yazdanmanesh.ui_heroList.HeroList
import com.yazdanmanesh.ui_heroList.HeroListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import coil.ImageLoader

class MainActivity : ComponentActivity() {
    private val state: MutableState<HeroListState> = mutableStateOf(HeroListState())
    private val progressBarState: MutableState<ProgressBarState> =
        mutableStateOf(ProgressBarState.Idle)
    private lateinit var imageLoader:ImageLoader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageLoader = ImageLoader.Builder(applicationContext)
            .error(R.drawable.error_image)
            .placeholder(R.drawable.white_background)
            .availableMemoryPercentage(0.25)
            .crossfade(true)
            .build()
        val getHeroes = HeroInteractions.build(
            sqlDriver = AndroidSqliteDriver(
                schema = HeroInteractions.schema,
                context = this,
                name = HeroInteractions.dbName
            )
        ).getHeroes
        val logger = Logger(tag = "Get Heroes Test")
        getHeroes.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    when (dataState.uiComponent) {
                        is UIComponent.Dialog -> {
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }
                        is UIComponent.None -> {
                            logger.log((dataState.uiComponent as UIComponent.None).message)
                        }
                    }
                }
                is DataState.Data -> {
                    Log.e("moz", "${dataState.data} " )
                    state.value = state.value.copy(heroes = dataState.data?: listOf())
                }

                is DataState.Loading -> {
                    progressBarState.value = dataState.progressBarState
                }
            }
        }.launchIn(CoroutineScope(IO))
        setContent {
            MultiModuleAndroidAppTheme {
                HeroList(
                    state = state.value,
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