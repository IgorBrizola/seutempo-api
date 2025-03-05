package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.adapters.repository.model.ClientEntity
import br.com.seutempo.api.adapters.web.model.response.client.ClientResponse

interface ManageClientInputPort {
    fun createUsersClient(clientEntity: ClientEntity)

    fun findClientById(id: Int): ClientResponse
}
