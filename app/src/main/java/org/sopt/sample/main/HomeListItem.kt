package org.sopt.sample.main

import androidx.annotation.DrawableRes

data class HomeListItem(
    val name: String,
    val body: String,
    @DrawableRes val imageNo: Int
) {
    companion object {
        fun of(name: String, body: String, imageNo: Int) = HomeListItem(name, body, imageNo)
    }
}