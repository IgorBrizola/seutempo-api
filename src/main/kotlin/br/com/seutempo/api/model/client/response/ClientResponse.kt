package br.com.seutempo.api.model.client.response

import br.com.seutempo.api.model.users.response.UsersResponse

data class ClientResponse(
    val id: Int?,
    val user: UsersResponse,
    val addresses: AddressResponse,
)
