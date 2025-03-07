package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.client.ClientJpaRepository
import br.com.seutempo.api.adapters.web.mapper.client.ClientMapper
import br.com.seutempo.api.core.domain.exceptions.ResourceNotFoundException
import br.com.seutempo.api.core.domain.model.client.Client
import br.com.seutempo.api.core.ports.output.ManageClientOutputPort
import org.springframework.stereotype.Repository

@Repository
class ManageClientRepository(
    private val clientJpaRepository: ClientJpaRepository,
    private val clientMapper: ClientMapper,
) : ManageClientOutputPort {
    override fun save(client: Client) {
        val clientEntity = clientMapper.toClientEntity(client)
        clientJpaRepository.save(clientEntity)
    }

    override fun findById(id: Int): Client {
        val clientEntity = clientJpaRepository.findById(id).orElseThrow { ResourceNotFoundException("User not found! - $id") }

        return clientMapper.toDomain(clientEntity)
    }
}
