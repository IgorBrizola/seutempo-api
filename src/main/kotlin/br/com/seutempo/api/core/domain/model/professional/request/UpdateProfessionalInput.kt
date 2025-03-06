package br.com.seutempo.api.core.domain.model.professional.request

import java.math.BigDecimal

data class UpdateProfessionalInput(
    val id: Int,
    val valueHour: BigDecimal?,
    val photoUser: String?,
)
