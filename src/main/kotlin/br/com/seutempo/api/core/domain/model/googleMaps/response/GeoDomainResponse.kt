package br.com.seutempo.api.core.domain.model.googleMaps.response

import org.locationtech.jts.geom.Point

data class GeoDomainResponse(
    val latitude: Double,
    val longitude: Double,
    val point: Point,
)
