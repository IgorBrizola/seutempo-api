package br.com.seutempo.api.model.address

data class AddressRequest(
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
