package br.com.seutempo.api.adapters.web.mapper.users

import br.com.seutempo.api.adapters.repository.model.ClientEntity
import br.com.seutempo.api.adapters.repository.model.Users
import br.com.seutempo.api.adapters.web.model.request.client.NewClientRequest
import br.com.seutempo.api.adapters.web.model.request.professional.NewProfessionalRequest
import br.com.seutempo.api.adapters.web.model.response.users.UsersResponse
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface UsersMapper {
    fun usersResponseToUsers(usersResponse: UsersResponse): Users

    fun usersToUsersResponse(users: Users): UsersResponse

    fun usersToProfessionalRequest(users: Users): NewProfessionalRequest

    fun usersProfessionalRequestToUsers(newProfessionalRequest: NewProfessionalRequest): Users

    fun clientToUsersClientRequest(clientEntity: ClientEntity): NewClientRequest

    fun usersClientRequestToUsers(newClientRequest: NewClientRequest): Users
}
