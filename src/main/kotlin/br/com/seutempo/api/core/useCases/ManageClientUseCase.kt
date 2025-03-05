package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.adapters.web.model.response.client.ClientResponse
import br.com.seutempo.api.core.domain.exceptions.ResourceAlreadyExistsException
import br.com.seutempo.api.core.domain.model.client.Client
import br.com.seutempo.api.core.ports.input.ManageClientInputPort
import br.com.seutempo.api.core.ports.output.ManageClientOutputPort
import br.com.seutempo.api.core.ports.output.ManageUsersOutputPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManageClientUseCase(
    private val clientJpaRepository: ManageClientOutputPort,
    private val usersJpaRepository: ManageUsersOutputPort,
) : ManageClientInputPort {
    @Transactional
    override fun createUsersClient(client: Client) {
        if (usersJpaRepository.existsByEmailAndActiveIsTrue(client.user.email)) {
            throw ResourceAlreadyExistsException("User with email '${client.user.email}' already exists.")
        }

        if (usersJpaRepository.existsByCpfAndActiveIsTrue(client.user.cpf)) {
            throw ResourceAlreadyExistsException("User with cpf '${client.user.cpf}' already exists.")
        }

        clientJpaRepository.save(client)
    }

    override fun findClientById(id: Int): ClientResponse = clientJpaRepository.findById(id)
}
