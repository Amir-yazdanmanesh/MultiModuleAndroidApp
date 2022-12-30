package com.yazdanmanesh.ui_heroDetail.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HeroDetail(
    heroId:Int?,
){
    Text(text = "Hero id: $heroId")
}