package br.com.seutempo.api.adapters.web.model.response.client

import br.com.seutempo.api.adapters.repository.model.TypeAddress
import org.locationtech.jts.geom.Point

data class AddressClientResponse(
    val cep: String,
    val state: String,
    val city: String,
    val neighborhood: String,
    val street: String,
    val number: Int,
    val complement: String,
    val additionalAddress: String? = null,
    val typeAddress: TypeAddress,
    val latitude: Double,
    val longitude: Double,
    val location: Point,
)
