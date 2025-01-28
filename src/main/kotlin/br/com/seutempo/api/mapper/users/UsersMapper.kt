package br.com.seutempo.api.mapper.users

import br.com.seutempo.api.model.client.Client
import br.com.seutempo.api.model.client.request.UsersClientRequestNew
import br.com.seutempo.api.model.professional.request.UsersProfessionalRequestNew
import br.com.seutempo.api.model.users.Users
import br.com.seutempo.api.model.users.response.UsersResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface UsersMapper {
    fun usersResponseToUsers(usersResponse: UsersResponse): Users

    @Mapping(target = "age", expression = "java(UsersResponse.Companion.calcAge(users.getDateAnniversary()))")
    fun usersToUsersResponse(users: Users): UsersResponse

    fun usersToProfessionalRequest(users: Users): UsersProfessionalRequestNew

    fun usersProfessionalRequestToUsers(usersProfessionalRequestNew: UsersProfessionalRequestNew): Users

    fun clientToUsersClientRequest(client: Client): UsersClientRequestNew

    fun usersClientRequestToUsers(usersClientRequestNew: UsersClientRequestNew): Users
}
