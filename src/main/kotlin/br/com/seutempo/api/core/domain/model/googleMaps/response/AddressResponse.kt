package br.com.seutempo.api.core.domain.model.googleMaps.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class AddressResponse(
    val cep: String,
    val city: String?,
    val state: String?,
    val formatedAddress: String?,
)
