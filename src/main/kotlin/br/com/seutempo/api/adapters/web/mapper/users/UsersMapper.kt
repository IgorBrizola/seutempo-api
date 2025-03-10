package br.com.seutempo.api.adapters.web.mapper.users

import br.com.seutempo.api.adapters.repository.model.ClientEntity
import br.com.seutempo.api.adapters.repository.model.UsersEntity
import br.com.seutempo.api.adapters.web.model.request.client.NewClientRequest
import br.com.seutempo.api.adapters.web.model.request.professional.NewProfessionalRequest
import br.com.seutempo.api.adapters.web.model.request.user.UpdatePasswordRequest
import br.com.seutempo.api.adapters.web.model.response.users.UsersResponse
import br.com.seutempo.api.core.domain.model.users.Users
import br.com.seutempo.api.core.domain.model.users.request.UpdatePasswordInput
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface UsersMapper {
    fun usersResponseToUsers(usersResponse: UsersResponse): UsersEntity

    fun usersToUsersResponse(usersEntity: UsersEntity): UsersResponse

    fun usersToProfessionalRequest(usersEntity: UsersEntity): NewProfessionalRequest

    fun usersProfessionalRequestToUsers(newProfessionalRequest: NewProfessionalRequest): Users

    fun clientToUsersClientRequest(clientEntity: ClientEntity): NewClientRequest

    fun usersClientRequestToUsers(newClientRequest: NewClientRequest): UsersEntity

    fun toUsers(usersEntity: UsersEntity): Users

    fun toUserEntity(users: Users): UsersEntity

    fun toListUsers(usersEntity: List<UsersEntity>): List<Users>

    fun toListUsersResponse(users: List<Users>): List<UsersResponse>

    fun toUpdatePasswordInput(
        id: Int,
        updatePasswordRequest: UpdatePasswordRequest,
    ): UpdatePasswordInput
}
