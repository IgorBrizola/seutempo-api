package br.com.seutempo.api.model.professional.response

import br.com.seutempo.api.model.specialty.response.SpecialtyResponse
import br.com.seutempo.api.model.users.response.UsersResponse
import java.math.BigDecimal

data class ProfessionalResponse(
    val user: UsersResponse,
    val linkNameProfessional: String,
    val urlProfessional: String,
    val valueHour: BigDecimal,
    val cep: String,
    val lat: BigDecimal,
    val lon: BigDecimal,
    val serviceRadiusKm: Int,
    val specialties: List<SpecialtyResponse>,
)
