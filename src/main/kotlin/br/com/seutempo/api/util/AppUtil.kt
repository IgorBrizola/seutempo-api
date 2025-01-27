package br.com.seutempo.api.util

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
}
