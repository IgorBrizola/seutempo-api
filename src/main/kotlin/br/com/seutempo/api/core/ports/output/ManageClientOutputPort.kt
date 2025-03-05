package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.repository.model.ClientEntity
import br.com.seutempo.api.adapters.web.model.response.client.ClientResponse

interface ManageClientOutputPort {
    fun save(clientEntity: ClientEntity): ClientEntity

    fun findById(id: Int): ClientResponse
}
