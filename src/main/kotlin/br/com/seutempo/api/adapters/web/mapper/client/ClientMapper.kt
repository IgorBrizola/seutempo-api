package br.com.seutempo.api.adapters.web.mapper.client

import br.com.seutempo.api.adapters.repository.model.ClientEntity
import br.com.seutempo.api.adapters.repository.model.UsersEntity
import br.com.seutempo.api.adapters.web.model.request.client.NewClientRequest
import br.com.seutempo.api.adapters.web.model.response.client.AddressClientResponse
import br.com.seutempo.api.core.domain.model.client.Client
import br.com.seutempo.api.core.domain.model.client.request.CreateClient
import br.com.seutempo.api.core.domain.model.users.Users
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface ClientMapper {
    fun usersToUsersClientRequest(usersEntity: UsersEntity): NewClientRequest

    fun usersClientRequestToUsers(newClientRequest: NewClientRequest): Users

    fun toUserEntity(user: Users): UsersEntity

    fun clientToUsersClientRequest(clientEntity: ClientEntity): NewClientRequest

    @Mapping(source = "user", target = "user")
    @Mapping(source = "newUsersClientRequest.surname", target = "surname")
    @Mapping(source = "newUsersClientRequest.address.cep", target = "cep")
    @Mapping(source = "newUsersClientRequest.address.state", target = "state")
    @Mapping(source = "newUsersClientRequest.address.city", target = "city")
    @Mapping(source = "newUsersClientRequest.address.neighborhood", target = "neighborhood")
    @Mapping(source = "newUsersClientRequest.address.street", target = "street")
    @Mapping(source = "newUsersClientRequest.address.number", target = "number")
    @Mapping(source = "newUsersClientRequest.address.complement", target = "complement")
    @Mapping(source = "newUsersClientRequest.address.additionalAddress", target = "additionalAddress")
    @Mapping(source = "newUsersClientRequest.address.typeAddress", target = "typeAddress")
    fun usersClientRequestToClient(
        user: Users,
        newUsersClientRequest: NewClientRequest,
    ): Client

    @Mapping(source = "user", target = "user")
    fun toClientEntity(client: Client): ClientEntity

    fun toAddressResponse(clientEntity: ClientEntity): AddressClientResponse

    fun toDomain(clientEntity: ClientEntity): Client

    @Mapping(source = "name", target = "user.name")
    @Mapping(source = "middleName", target = "user.middleName")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "email", target = "user.email")
    @Mapping(source = "password", target = "user.password")
    @Mapping(source = "photoUser", target = "user.photoUser")
    @Mapping(source = "cpf", target = "user.cpf")
    @Mapping(source = "typeUser", target = "user.typeUser")
    @Mapping(source = "createdAt", target = "user.createdAt")
    @Mapping(source = "active", target = "user.active")
    @Mapping(source = "dateAnniversary", target = "user.dateAnniversary")
    @Mapping(source = "address.cep", target = "cep")
    @Mapping(source = "address.state", target = "state")
    @Mapping(source = "address.city", target = "city")
    @Mapping(source = "address.neighborhood", target = "neighborhood")
    @Mapping(source = "address.street", target = "street")
    @Mapping(source = "address.number", target = "number")
    @Mapping(source = "address.complement", target = "complement")
    @Mapping(source = "address.additionalAddress", target = "additionalAddress")
    @Mapping(source = "address.typeAddress", target = "typeAddress")
    fun toCrate(newClientRequest: NewClientRequest): CreateClient

    fun createToClient(createClient: CreateClient): Client
}
