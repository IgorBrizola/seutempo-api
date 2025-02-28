package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.client.ClientJpaRepository
import br.com.seutempo.api.adapters.repository.model.ClientEntity
import br.com.seutempo.api.core.ports.output.ManageClientOutputPort
import java.util.Optional

class ManageClientRepository(
    private val clientJpaRepository: ClientJpaRepository,
) : ManageClientOutputPort {
    override fun save(clientEntity: ClientEntity): ClientEntity = clientJpaRepository.save(clientEntity)

    override fun findById(id: Int): Optional<ClientEntity> = clientJpaRepository.findById(id)
}
