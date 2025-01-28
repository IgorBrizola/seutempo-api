package br.com.seutempo.api.util

import java.text.Normalizer

object AppUtil {
    fun removeAccents(name: String): String =
        Normalizer
            .normalize(name, Normalizer.Form.NFD)
            .replace(" ", "-")
            .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
            .lowercase()
}
