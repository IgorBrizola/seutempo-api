package br.com.seutempo.api.adapters.integration.model.response

import org.locationtech.jts.geom.Point

data class GeoResponse(
    val latitude: Double,
    val longitude: Double,
    val point: Point,
)
