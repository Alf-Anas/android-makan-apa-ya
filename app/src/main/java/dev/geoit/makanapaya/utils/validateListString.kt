package dev.geoit.makanapaya.utils

fun validateListString(stringList: List<String>): Boolean {
    if (stringList.isEmpty()) {
        return false
    }

    for (string in stringList) {
        if (string.isBlank()) {
            return false
        }
    }

    return true
}
