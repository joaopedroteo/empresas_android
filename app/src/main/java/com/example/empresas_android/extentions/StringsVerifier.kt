package com.example.empresas_android.extentions

import java.util.regex.Pattern

fun String.hasNumber(): Boolean {
    val passwordPattern = "(?=.*[0-9]).*"
    return Pattern.compile(passwordPattern).matcher(this).matches()
}

fun String.hasUpperCase(): Boolean {
    val passwordPattern = "(?=.*[A-Z]).*"
    return Pattern.compile(passwordPattern).matcher(this).matches()
}

fun String.hasLowerCase(): Boolean {
    val passwordPattern = "(?=.*[a-z]).*"
    return Pattern.compile(passwordPattern).matcher(this).matches()
}

fun String.hasEspecialCharacters(): Boolean {
    val passwordPattern = "(?=.*[&*+\$#!%]).*"
    return Pattern.compile(passwordPattern).matcher(this).matches()
}

fun String.hasSixCharacter(): Boolean {
    val passwordPattern = ".{6}.*"
    return Pattern.compile(passwordPattern).matcher(this).matches()
}

fun String.isEmail(): Boolean {
    val emailPatterns = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
    return Pattern.compile(emailPatterns).matcher(this).matches()
}