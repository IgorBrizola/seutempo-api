package br.com.seutempo.api.adapters.web.model.request.client

import br.com.seutempo.api.adapters.repository.model.TypeAddress

data class AddressClientRequest(
    val cep: String,
    val state: String,
    val city: String,
    val neighborhood: String,
    val street: String,
    val number: Int,
    val complement: String,
    val additionalAddress: String? = null,
    val typeAddress: TypeAddress,
)
