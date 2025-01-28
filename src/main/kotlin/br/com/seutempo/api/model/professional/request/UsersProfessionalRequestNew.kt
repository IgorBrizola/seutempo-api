package br.com.seutempo.api.model.professional.request

import java.math.BigDecimal
import java.time.LocalDate

data class UsersProfessionalRequestNew(
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val cpf: String,
    val phone: String,
    val dateAnniversary: LocalDate,
    val valueHour: BigDecimal,
)
