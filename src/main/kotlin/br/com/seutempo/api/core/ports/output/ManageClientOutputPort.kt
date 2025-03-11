package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.core.domain.model.client.Client

interface ManageClientOutputPort {
    fun save(client: Client)

    fun findById(id: Int): Client

    fun listAllClients(): List<Client>
}
