package br.com.seutempo.api.model.address.response

import br.com.seutempo.api.model.address.TypeAddress

data class AddressResponse(
    val cep: String,
    val state: String,
    val city: String,
    val neighborhood: String,
    val street: String,
    val number: Int,
    val complement: String,
    val additionalAddress: String,
    val typeAddress: TypeAddress,
)
