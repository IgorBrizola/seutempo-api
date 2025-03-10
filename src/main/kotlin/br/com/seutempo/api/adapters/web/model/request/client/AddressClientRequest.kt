package br.com.seutempo.api.adapters.web.model.request.client

import br.com.seutempo.api.adapters.repository.model.TypeAddress
import io.swagger.v3.oas.annotations.media.Schema

data class AddressClientRequest(
    @Schema(defaultValue = "03504010")
    val cep: String,
    @Schema(defaultValue = "Vila Aricanduva")
    val neighborhood: String,
    @Schema(defaultValue = "Rua guaperuvu")
    val street: String,
    @Schema(defaultValue = "421")
    val number: Int,
    @Schema(defaultValue = "Apto 1")
    val complement: String,
    @Schema(hidden = true)
    val additionalAddress: String? = null,
    @Schema(defaultValue = "RESIDENTIAL")
    val typeAddress: TypeAddress,
)
