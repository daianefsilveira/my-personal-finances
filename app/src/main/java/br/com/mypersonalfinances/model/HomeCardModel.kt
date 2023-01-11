package br.com.mypersonalfinances.model

import android.graphics.Color
import android.graphics.drawable.Drawable

data class HomeCardModel(
    val title: String,
    val amount: String,
    val imagem: Drawable,
    val backgroundColor: Int,
)
