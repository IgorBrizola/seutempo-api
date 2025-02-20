package br.com.seutempo.api.mapper.client

import br.com.seutempo.api.model.client.Client
import br.com.seutempo.api.model.client.request.UsersClientRequestNew
import br.com.seutempo.api.model.client.response.AddressResponse
import br.com.seutempo.api.model.users.Users
import org.locationtech.jts.geom.Point
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
    @Mapping(source = "newUsersClientRequest.address.cep", target = "cep")
    @Mapping(source = "newUsersClientRequest.address.state", target = "state")
    @Mapping(source = "newUsersClientRequest.address.city", target = "city")
    @Mapping(source = "newUsersClientRequest.address.neighborhood", target = "neighborhood")
    @Mapping(source = "newUsersClientRequest.address.street", target = "street")
    @Mapping(source = "newUsersClientRequest.address.number", target = "number")
    @Mapping(source = "newUsersClientRequest.address.complement", target = "complement")
    @Mapping(source = "newUsersClientRequest.address.additionalAddress", target = "additionalAddress")
    @Mapping(source = "newUsersClientRequest.address.typeAddress", target = "typeAddress")
    @Mapping(source = "lat", target = "latitude")
    @Mapping(source = "lon", target = "longitude")
    @Mapping(source = "location", target = "location")
    fun usersClientRequestToClient(
        user: Users,
        newUsersClientRequest: UsersClientRequestNew,
        lat: Double,
        lon: Double,
        location: Point,
    ): Client

    fun toAddressResponse(client: Client): AddressResponse
}
