package br.com.seutempo.api.service.client

import br.com.seutempo.api.exception.users.UserAlreadyExistsException
import br.com.seutempo.api.mapper.client.ClientMapper
import br.com.seutempo.api.model.client.request.UsersClientRequestNew
import br.com.seutempo.api.repository.client.ClientRepository
import br.com.seutempo.api.repository.users.UsersRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClientService(
    private val clientRepository: ClientRepository,
    private val usersRepository: UsersRepository,
    private val clientMapper: ClientMapper,
) {
    @Transactional
    fun createUsersClient(newUsersClientRequest: UsersClientRequestNew) {
        if (usersRepository.existsByEmailAndActiveIsTrue(newUsersClientRequest.email)) {
            throw UserAlreadyExistsException("User with email '${newUsersClientRequest.email}' already exists.")
        }

        if (usersRepository.existsByCpfAndActiveIsTrue(newUsersClientRequest.cpf)) {
            throw UserAlreadyExistsException("User with cpf '${newUsersClientRequest.cpf}' already exists.")
        }

        val user = clientMapper.usersClientRequestToUsers(newUsersClientRequest)

        val client = clientMapper.usersClientRequestToClient(user, newUsersClientRequest)

        clientMapper.usersClientRequestToAddress(newUsersClientRequest, client)

        clientRepository.save(client)
    }
}
