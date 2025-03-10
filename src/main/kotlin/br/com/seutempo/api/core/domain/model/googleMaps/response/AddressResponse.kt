package br.com.seutempo.api.core.domain.model.googleMaps.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import org.locationtech.jts.geom.Point

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class AddressResponse(
    val cep: String,
    val city: String?,
    val state: String?,
    val formatedAddress: String?,
    var lat: Double?,
    var lon: Double?,
    var location: Point?,
)
