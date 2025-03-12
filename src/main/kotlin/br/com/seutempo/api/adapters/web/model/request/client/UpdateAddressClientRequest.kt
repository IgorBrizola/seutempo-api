package br.com.seutempo.api.adapters.web.model.request.client

import br.com.seutempo.api.adapters.repository.model.TypeAddress
import io.swagger.v3.oas.annotations.media.Schema

data class UpdateAddressClientRequest(
    val cep: String,
    val neighborhood: String,
    val street: String,
    val number: Int,
    val complement: String?,
    val additionalAddress: String?,
    @Schema(defaultValue = "RESIDENTIAL")
    val typeAddress: TypeAddress,
)
