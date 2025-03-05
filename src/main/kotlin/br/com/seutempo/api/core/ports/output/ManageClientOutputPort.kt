package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.web.model.response.client.ClientResponse
import br.com.seutempo.api.core.domain.model.Client

interface ManageClientOutputPort {
    fun save(client: Client)

    fun findById(id: Int): ClientResponse
}
