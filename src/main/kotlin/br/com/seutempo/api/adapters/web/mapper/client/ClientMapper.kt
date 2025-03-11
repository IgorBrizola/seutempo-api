package br.com.seutempo.api.adapters.web.mapper.client

import br.com.seutempo.api.adapters.repository.model.ClientEntity
import br.com.seutempo.api.adapters.repository.model.UsersEntity
import br.com.seutempo.api.adapters.web.model.request.client.NewClientRequest
import br.com.seutempo.api.adapters.web.model.request.client.UpdateClientRequest
import br.com.seutempo.api.adapters.web.model.response.client.AddressClientResponse
import br.com.seutempo.api.adapters.web.model.response.client.ClientResponse
import br.com.seutempo.api.core.domain.model.client.Client
import br.com.seutempo.api.core.domain.model.client.request.CreateClient
import br.com.seutempo.api.core.domain.model.client.request.UpdateClient
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
    fun usersClientRequestToUsers(newClientRequest: NewClientRequest): Users

    fun toUserEntity(user: Users): UsersEntity

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
    @Mapping(source = "phone", target = "user.phone")
    @Mapping(source = "typeUser", target = "user.typeUser")
    @Mapping(source = "createdAt", target = "user.createdAt")
    @Mapping(source = "active", target = "user.active")
    @Mapping(source = "dateAnniversary", target = "user.dateAnniversary")
    @Mapping(source = "address.cep", target = "cep")
    @Mapping(source = "address.neighborhood", target = "neighborhood")
    @Mapping(source = "address.street", target = "street")
    @Mapping(source = "address.number", target = "number")
    @Mapping(source = "address.complement", target = "complement")
    @Mapping(source = "address.additionalAddress", target = "additionalAddress")
    @Mapping(source = "address.typeAddress", target = "typeAddress")
    fun toCrate(newClientRequest: NewClientRequest): CreateClient

    fun createToClient(createClient: CreateClient): Client

    @Mapping(source = "cep", target = "address.cep")
    @Mapping(source = "neighborhood", target = "address.neighborhood")
    @Mapping(source = "street", target = "address.street")
    @Mapping(source = "number", target = "address.number")
    @Mapping(source = "complement", target = "address.complement")
    @Mapping(source = "additionalAddress", target = "address.additionalAddress")
    @Mapping(source = "typeAddress", target = "address.typeAddress")
    @Mapping(source = "state", target = "address.state")
    @Mapping(source = "city", target = "address.city")
    @Mapping(source = "formatedAddress", target = "address.formatedAddress")
    @Mapping(source = "latitude", target = "address.latitude")
    @Mapping(source = "longitude", target = "address.longitude")
    fun toResponse(client: Client): ClientResponse

    fun toListResponse(clients: List<Client>): List<ClientResponse>

    fun toListClient(clientsEntity: List<ClientEntity>): List<Client>

    fun toUpdateClient(
        id: Int,
        updateClientRequest: UpdateClientRequest,
    ): UpdateClient
}
