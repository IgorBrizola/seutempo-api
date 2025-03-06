package br.com.seutempo.api.core.domain.model.professional

import br.com.seutempo.api.core.domain.model.specialty.Specialty
import br.com.seutempo.api.core.domain.model.users.Users
import org.locationtech.jts.geom.Point
import java.math.BigDecimal

data class Professional(
    val id: Int?,
    val user: Users,
    var urlProfessional: String? = null,
    var linkNameProfessional: String? = null,
    val valueHour: BigDecimal,
    val specialties: MutableList<Specialty>,
    val cep: String,
    var lat: Double? = null,
    var lon: Double? = null,
    var location: Point? = null,
    val serviceRadiusKm: Int,
)
