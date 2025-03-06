package br.com.seutempo.api.core.domain.model.users.request

import br.com.seutempo.api.core.domain.model.users.Users
import org.locationtech.jts.geom.Point
import java.math.BigDecimal

data class CreateNewProfessional(
    val user: Users,
    val urlProfessional: String,
    val linkNameProfessional: String,
    val valueHour: BigDecimal,
    val specialtiesIds: List<Int>,
    val cep: String,
    val lat: Double,
    val lon: Double,
    val serviceRadiusKm: Int,
    val location: Point,
)
