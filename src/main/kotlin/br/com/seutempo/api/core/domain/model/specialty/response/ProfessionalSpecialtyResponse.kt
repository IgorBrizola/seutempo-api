package br.com.seutempo.api.core.domain.model.specialty.response

import br.com.seutempo.api.core.domain.model.users.Users
import java.math.BigDecimal

data class ProfessionalSpecialtyResponse(
    val id: Int?,
    val user: Users,
    var urlProfessional: String? = null,
    var linkNameProfessional: String? = null,
    val valueHour: BigDecimal,
)
