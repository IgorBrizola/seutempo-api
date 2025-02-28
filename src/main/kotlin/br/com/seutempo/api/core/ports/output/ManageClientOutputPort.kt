package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.repository.model.ClientEntity
import java.util.Optional

interface ManageClientOutputPort {
    fun save(clientEntity: ClientEntity): ClientEntity

    fun findById(id: Int): Optional<ClientEntity>
}
