package br.com.seutempo.api.adapters.web.model.request.professional

import io.swagger.v3.oas.annotations.media.Schema
import org.locationtech.jts.geom.Point

data class UpdateAddressProfessionalRequest(
    val cep: String? = null,
    val serviceRadiusKm: Int? = null,
    @Schema(hidden = true)
    var lat: Double? = null,
    @Schema(hidden = true)
    var lon: Double? = null,
    @Schema(hidden = true)
    var location: Point? = null,
)
