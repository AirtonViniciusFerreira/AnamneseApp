package com.example.anamnesedrapp.util

fun ValidarCpfCnpj(cpfCnpj: String): String {
    var sRetorno: String = ""
    if (cpfCnpj.length < 11) sRetorno = ValidarCpf(cpfCnpj)
    else sRetorno = ValidarCnpj(cpfCnpj)

    return sRetorno
}

fun ValidarCpf(cpf: String): String {
    var sRetorno: String = ""
    if (sRetorno.length != 11) return "Cpf ou Cnpj Invalidos!"

    var valido: Double = 0.0
    var count: Double = 10.0
    for (i in cpf.indices) {
        valido += cpf[i].toString().toDouble() * count
        count--
        if (i == 8) break
    }
    var resto: Double = (valido * 10) % 11
    if (resto != cpf.substring(9..10).toDouble()) return "Cpf invalido!"
    count = 11.0
    valido = 0.0
    for (i in cpf.indices) {
        valido += cpf[i].toString().toDouble() * count
        count--
        if (i==9) break
    }
    resto = (valido*10) % 11
    if (resto != cpf.substring(10..11).toDouble()) return "Cpf invalido!"
    return sRetorno
}

fun ValidarCnpj(Cnpj: String): String {
    var sRetorno: String = ""

    return sRetorno
}