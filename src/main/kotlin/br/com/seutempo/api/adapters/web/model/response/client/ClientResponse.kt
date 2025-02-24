package br.com.seutempo.api.adapters.web.model.response.client

import br.com.seutempo.api.adapters.web.model.response.users.UsersResponse

data class ClientResponse(
    val id: Int?,
    val user: UsersResponse,
    val address: AddressClientResponse,
)
