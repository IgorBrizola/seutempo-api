package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.client.ClientJpaRepository
import br.com.seutempo.api.adapters.repository.model.ClientEntity
import br.com.seutempo.api.adapters.web.mapper.client.ClientMapper
import br.com.seutempo.api.adapters.web.mapper.users.UsersMapper
import br.com.seutempo.api.adapters.web.model.response.client.ClientResponse
import br.com.seutempo.api.core.domain.exceptions.ResourceNotFoundException
import br.com.seutempo.api.core.ports.output.ManageClientOutputPort

class ManageClientRepository(
    private val clientJpaRepository: ClientJpaRepository,
    private val usersMapper: UsersMapper,
    private val clientMapper: ClientMapper,
) : ManageClientOutputPort {
    override fun save(clientEntity: ClientEntity): ClientEntity = clientJpaRepository.save(clientEntity)

    override fun findById(id: Int): ClientResponse {
        val clientEntity = clientJpaRepository.findById(id).orElseThrow { ResourceNotFoundException("User not found! - $id") }
        val userResponse = usersMapper.usersToUsersResponse(clientEntity.user)
        return ClientResponse(
            id = clientEntity.id,
            user = userResponse,
            address = clientMapper.toAddressResponse(clientEntity),
        )
    }
}
