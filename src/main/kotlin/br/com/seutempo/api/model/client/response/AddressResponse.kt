package br.com.seutempo.api.model.client.response

import br.com.seutempo.api.model.client.TypeAddress
import org.locationtech.jts.geom.Point

data class AddressResponse(
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
