package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.adapters.web.model.response.client.ClientResponse
import br.com.seutempo.api.core.domain.model.Client

interface ManageClientInputPort {
    fun createUsersClient(client: Client)

    fun findClientById(id: Int): ClientResponse
}
