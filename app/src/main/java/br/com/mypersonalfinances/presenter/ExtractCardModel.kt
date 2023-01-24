package br.com.mypersonalfinances.presenter

import android.graphics.drawable.Drawable

class ExtractCardModel(
    var description: String,
    var amount: String,
    var category: String,
    var date: String,
    val backgroundColor: Int? = null
)