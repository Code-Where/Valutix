package com.abhishek.vaultIx.util

import kotlin.random.Random

enum class PasswordStrength{
    WEAK,
    MEDIUM,
    STRONG,
    VERY_STRONG
}

object PasswordHelper{
    fun calculatePasswordStrength(password: String): PasswordStrength {
        var score = 0;
        if (password.length > 8) { score++ }
        if (password.length >= 12) { score++ }
        if (password.any { it.isUpperCase() }) { score++ }
        if (password.any { it.isLowerCase() }) { score++ }
        if (password.any { it.isDigit() }) { score++ }
        if (password.any { !it.isLetterOrDigit() }) { score++ }

        return when(score){
            in 0..2 -> PasswordStrength.WEAK
            in 3..4 -> PasswordStrength.MEDIUM
            5 -> PasswordStrength.STRONG
            else -> PasswordStrength.VERY_STRONG
        }
    }

    fun generatePassword(length: Int): String {
        require(length >= 12) { "Length must be at least 12" }

        val lowerLetters = ('a'..'z')
        val upperLetters = ('A'..'Z')
        val numbers = ('0'..'9')
        val specialChars = ('!'..'/') + (':'..'@') + ("[".."`") + ("{".."~")

        val allChars = lowerLetters + upperLetters + numbers + specialChars

        val password = StringBuilder()
        password.append(lowerLetters.random())
        password.append(upperLetters.random())
        password.append(numbers.random())
        password.append(specialChars.random())

        while (password.length < length) {
            password.append(allChars.random())
        }

        return password.toMutableList().shuffled(Random).joinToString("")

    }
}
