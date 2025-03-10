package br.com.seutempo.api.util

import java.text.Normalizer

object AppUtil {
    private val phoneRegex = Regex("""^\+\d{1,3} \(\d{2,3}\) \d{4,5}-\d{4}$""")
    private val cpfRegex = Regex("""^\d{3}\.\d{3}\.\d{3}-\d{2}$""")

    fun removeAccents(name: String): String =
        Normalizer
            .normalize(name, Normalizer.Form.NFD)
            .replace(" ", "-")
            .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
            .lowercase()

    fun isValidPhoneNumber(phone: String): Boolean = phoneRegex.matches(phone)

    fun isValidCpf(cpf: String): Boolean = cpfRegex.matches(cpf)

    fun isCpfExist(cpf: String): Boolean {
        val numbers = cpf.replace(".", "").replace("-", "").map { it.toString().toInt() }

        if (numbers.distinct().size == 1) return false

        val firstSum = (0..8).sumOf { numbers[it] * (10 - it) }
        val firstDigit = (firstSum * 10) % 11 % 10

        val secondSum = (0..9).sumOf { numbers[it] * (11 - it) }
        val secondDigit = (secondSum * 10) % 11 % 10

        return numbers[9] == firstDigit && numbers[10] == secondDigit
    }
}
