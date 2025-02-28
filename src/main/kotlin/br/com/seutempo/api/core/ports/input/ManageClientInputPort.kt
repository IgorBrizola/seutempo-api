package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.adapters.web.model.request.client.NewClientRequest
import br.com.seutempo.api.adapters.web.model.response.client.ClientResponse

interface ManageClientInputPort {
    fun createUsersClient(newUsersClientRequest: NewClientRequest)

    fun findClientById(id: Int): ClientResponse
}
