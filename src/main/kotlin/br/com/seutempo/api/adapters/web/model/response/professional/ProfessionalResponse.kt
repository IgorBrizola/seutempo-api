package br.com.seutempo.api.adapters.web.model.response.professional

import br.com.seutempo.api.adapters.web.model.response.specialty.SpecialtyResponse
import br.com.seutempo.api.adapters.web.model.response.users.UsersResponse
import java.math.BigDecimal

data class ProfessionalResponse(
    val user: UsersResponse,
    val linkNameProfessional: String,
    val urlProfessional: String,
    val valueHour: BigDecimal,
    val cep: String,
    val lat: Double,
    val lon: Double,
    val serviceRadiusKm: Int,
    val specialties: List<SpecialtyResponse>,
    val active: Boolean,
)
