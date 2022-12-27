package com.yazdanmanesh.ui_heroDetail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HeroDetail(
    heroId:Int?,
){
    Text(text = "Hero id: $heroId")
}