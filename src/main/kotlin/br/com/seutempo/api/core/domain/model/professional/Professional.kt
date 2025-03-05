package br.com.seutempo.api.core.domain.model.professional

import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity
import br.com.seutempo.api.adapters.repository.model.UsersEntity
import org.locationtech.jts.geom.Point
import java.math.BigDecimal

data class Professional(
    val id: Int,
    val user: UsersEntity,
    val urlProfessional: String,
    val linkNameProfessional: String,
    val valueHour: BigDecimal,
    val specialties: MutableList<SpecialtyEntity>,
    val cep: String,
    val lat: Double,
    val lon: Double,
    val serviceRadiusKm: Int,
    val location: Point,
)
