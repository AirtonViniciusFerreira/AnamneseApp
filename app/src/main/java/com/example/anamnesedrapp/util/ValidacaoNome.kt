package com.example.anamnesedrapp.util

fun ValidarNome(nome:String): String {
    var retorno : String = ""
    retorno = ValidaNomeVazio(nome = nome)
    if (retorno.isNotBlank()) return retorno

//    retorno = ValidaNomeCompleto(nome = nome)
    return  retorno
}

fun ValidaNomeVazio(nome: String): String {
    if (nome.isBlank()) return "O campo não pode ser vazio"
    return ""
}

fun ValidaNomeCompleto(nome: String): String {
    var regex = Regex("^[a-záàâãéèêíïóôõöúçñ]{3,}(\\s[a-záàâãéèêíïóôõöúçñ]{2,})+\$")
    if (!nome.matches(regex)) return  "O campo precisa ser digitado corretamente!"
    return ""
}