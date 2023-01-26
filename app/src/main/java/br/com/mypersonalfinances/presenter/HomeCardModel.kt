package br.com.mypersonalfinances.presenter

data class HomeCardModel(
    var title: String? = null,
    var amount: String,
    val imagem: Int = 0,
    val backgroundColor: Int? = null,
)
