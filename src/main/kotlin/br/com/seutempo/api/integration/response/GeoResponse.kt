package br.com.seutempo.api.integration.response

import org.locationtech.jts.geom.Point

data class GeoResponse(
    val latitude: Double,
    val longitude: Double,
    val point: Point,
)
