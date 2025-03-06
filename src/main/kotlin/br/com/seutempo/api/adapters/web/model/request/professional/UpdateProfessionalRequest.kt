package br.com.seutempo.api.adapters.web.model.request.professional

import java.math.BigDecimal

data class UpdateProfessionalRequest(
    val valueHour: BigDecimal? = null,
    val photoUser: String? = null,
)
