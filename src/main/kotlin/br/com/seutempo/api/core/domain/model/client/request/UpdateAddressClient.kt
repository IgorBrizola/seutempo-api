package br.com.seutempo.api.core.domain.model.client.request

import br.com.seutempo.api.adapters.repository.model.TypeAddress

data class UpdateAddressClient(
    val id: Int,
    val cep: String,
    val neighborhood: String,
    val street: String,
    val number: Int,
    val complement: String?,
    val additionalAddress: String?,
    val typeAddress: TypeAddress,
)
