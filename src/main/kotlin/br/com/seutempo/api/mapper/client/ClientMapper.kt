package br.com.seutempo.api.mapper.client

import br.com.seutempo.api.model.address.Address
import br.com.seutempo.api.model.client.Client
import br.com.seutempo.api.model.client.request.UsersClientRequestNew
import br.com.seutempo.api.model.users.Users
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface ClientMapper {
    fun usersToUsersClientRequest(users: Users): UsersClientRequestNew

    fun usersClientRequestToUsers(usersClientRequestNew: UsersClientRequestNew): Users

    fun clientToUsersClientRequest(client: Client): UsersClientRequestNew

    @Mapping(source = "user", target = "user")
    @Mapping(source = "newUsersClientRequest.surname", target = "surname")
    fun usersClientRequestToClient(
        user: Users,
        newUsersClientRequest: UsersClientRequestNew,
    ): Client

    fun addressToUsersClientRequest(address: Address): UsersClientRequestNew

    @Mapping(source = "newUsersClientRequest.address.cep", target = "cep")
    @Mapping(source = "newUsersClientRequest.address.state", target = "state")
    @Mapping(source = "newUsersClientRequest.address.city", target = "city")
    @Mapping(source = "newUsersClientRequest.address.neighborhood", target = "neighborhood")
    @Mapping(source = "newUsersClientRequest.address.street", target = "street")
    @Mapping(source = "newUsersClientRequest.address.number", target = "number")
    @Mapping(source = "newUsersClientRequest.address.complement", target = "complement")
    @Mapping(source = "newUsersClientRequest.address.additionalAddress", target = "additionalAddress")
    @Mapping(source = "newUsersClientRequest.address.typeAddress", target = "typeAddress")
    @Mapping(source = "client", target = "client")
    fun usersClientRequestToAddress(
        newUsersClientRequest: UsersClientRequestNew,
        client: Client,
    ): Address
}
