package br.com.mypersonalfinances.presenter

import android.graphics.drawable.Drawable

data class HomeCardModel(
    var title: String? = null,
    var amount: String,
    val imagem: Drawable? = null,
    val backgroundColor: Int? = null,
)
