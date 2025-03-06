package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.core.domain.model.client.Client

interface ManageClientInputPort {
    fun createUsersClient(client: Client)

    fun findClientById(id: Int): Client
}
