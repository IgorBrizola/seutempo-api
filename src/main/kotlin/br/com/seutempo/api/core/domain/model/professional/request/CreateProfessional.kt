package br.com.seutempo.api.core.domain.model.professional.request

import br.com.seutempo.api.core.domain.model.users.request.CreateUser
import java.math.BigDecimal

data class CreateProfessional(
    val user: CreateUser,
    val valueHour: BigDecimal,
    val specialtyIds: List<Int>,
    val cep: String,
    val serviceRadiusKm: Int,
)
