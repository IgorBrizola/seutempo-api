package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.adapters.repository.jpa.client.ClientJpaRepository
import br.com.seutempo.api.adapters.repository.jpa.users.UsersJpaRepository
import br.com.seutempo.api.adapters.web.mapper.client.ClientMapper
import br.com.seutempo.api.adapters.web.mapper.users.UsersMapper
import br.com.seutempo.api.adapters.web.model.request.client.NewClientRequest
import br.com.seutempo.api.adapters.web.model.response.client.ClientResponse
import br.com.seutempo.api.core.domain.exceptions.ResourceAlreadyExistsException
import br.com.seutempo.api.core.domain.exceptions.ResourceNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManageClientUseCase(
    private val clientJpaRepository: ClientJpaRepository,
    private val usersJpaRepository: UsersJpaRepository,
    private val clientMapper: ClientMapper,
    private val usersMapper: UsersMapper,
    private val manageUsersUseCase: ManageUsersUseCase,
) {
    @Transactional
    fun createUsersClient(newUsersClientRequest: NewClientRequest) {
        if (usersJpaRepository.existsByEmailAndActiveIsTrue(newUsersClientRequest.email)) {
            throw ResourceAlreadyExistsException("User with email '${newUsersClientRequest.email}' already exists.")
        }

        if (usersJpaRepository.existsByCpfAndActiveIsTrue(newUsersClientRequest.cpf)) {
            throw ResourceAlreadyExistsException("User with cpf '${newUsersClientRequest.cpf}' already exists.")
        }

        val user = clientMapper.usersClientRequestToUsers(newUsersClientRequest)

        val geometry = manageUsersUseCase.convertLocationGeo(newUsersClientRequest.address.cep)

        val point = manageUsersUseCase.convertGeometryPoint(geometry)

        val client =
            clientMapper.usersClientRequestToClient(
                user,
                newUsersClientRequest,
                lat = geometry.location.lat,
                lon = geometry.location.lng,
                location = point,
            )
        clientJpaRepository.save(client)
    }

    fun findClientById(id: Int): ClientResponse {
        val client = clientJpaRepository.findById(id).orElseThrow { throw ResourceNotFoundException("Client not found!") }
        return ClientResponse(
            id = client.id,
            user = usersMapper.usersToUsersResponse(client.user),
            address = clientMapper.toAddressResponse(client),
        )
    }
}
