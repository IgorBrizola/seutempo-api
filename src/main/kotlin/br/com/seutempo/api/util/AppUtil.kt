package br.com.seutempo.api.util

import java.text.Normalizer
import java.time.LocalDate
import java.time.Period

object AppUtil {
    fun calcAge(
        dateBirth: LocalDate,
        today: LocalDate = LocalDate.now(),
    ): Int {
        val age = Period.between(dateBirth, today).years
        return age
    }

    fun removeAccents(name: String): String =
        Normalizer
            .normalize(name, Normalizer.Form.NFD)
            .replace(" ", "-")
            .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
            .lowercase()
}
