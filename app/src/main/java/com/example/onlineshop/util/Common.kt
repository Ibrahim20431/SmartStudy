package com.example.onlineshop.util

import androidx.compose.ui.graphics.Color
import com.example.onlineshop.presentation.ui.theme.Green
import com.example.onlineshop.presentation.ui.theme.Orange
import com.example.onlineshop.presentation.ui.theme.Red

enum class Priority(
    val title: String,
    val color: Color,
    val value: Int
){
    LOW(title = "Low", color = Green, value = 0),
    MEDIUM(title = "Medium", color = Orange, value = 1),
    HIGH(title = "High", color = Red, value = 2);

    companion object {
        fun fromInt(value: Int) = entries.firstOrNull() {it.value == value} ?: MEDIUM
    }
}