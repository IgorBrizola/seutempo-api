package br.com.seutempo.api.service.client

import br.com.seutempo.api.mapper.client.ClientMapper
import br.com.seutempo.api.mapper.users.UsersMapper
import br.com.seutempo.api.model.client.request.UsersClientRequestNew
import br.com.seutempo.api.model.client.response.ClientResponse
import br.com.seutempo.api.model.exception.users.UserAlreadyExistsException
import br.com.seutempo.api.repository.client.ClientRepository
import br.com.seutempo.api.repository.users.UsersRepository
import br.com.seutempo.api.service.users.UsersService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClientService(
    private val clientRepository: ClientRepository,
    private val usersRepository: UsersRepository,
    private val clientMapper: ClientMapper,
    private val usersMapper: UsersMapper,
    private val usersService: UsersService,
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

        val geometry = usersService.convertLocationGeo(newUsersClientRequest.address.cep)

        val point = usersService.convertGeometryPoint(geometry)

        val client =
            clientMapper.usersClientRequestToClient(
                user,
                newUsersClientRequest,
                lat = geometry.location.lat,
                lon = geometry.location.lng,
                location = point,
            )
        clientRepository.save(client)
    }

    fun findClientById(id: Int): ClientResponse {
        val client = clientRepository.findById(id).orElseThrow { throw UserAlreadyExistsException("Client not found!") }
        return ClientResponse(
            id = client.id,
            user = usersMapper.usersToUsersResponse(client.user),
            addresses = clientMapper.toAddressResponse(client),
        )
    }
}
