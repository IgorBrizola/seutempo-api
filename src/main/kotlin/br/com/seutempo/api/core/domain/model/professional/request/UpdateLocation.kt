package br.com.seutempo.api.core.domain.model.professional.request

import org.locationtech.jts.geom.Point

data class UpdateLocation(
    val id: Int,
    val cep: String?,
    val serviceRadiusKm: Int?,
    var city: String? = null,
    var state: String? = null,
    var formatedAddress: String? = null,
    var lat: Double? = null,
    var lon: Double? = null,
    var location: Point? = null,
)
