package dev.geoit.makanapaya.utils

fun validateInteger(input: String): Boolean {
    if (input.isBlank()) {
        return false
    }

    return try {
        input.toInt()
        true
    } catch (e: Exception) {
        false
    }
}