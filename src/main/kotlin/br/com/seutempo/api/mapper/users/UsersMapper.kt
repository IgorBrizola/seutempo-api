package br.com.seutempo.api.mapper.users

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
    fun toDomainUser(usersResponse: UsersResponse): Users

    @Mapping(target = "age", expression = "java(UsersResponse.Companion.calcAge(users.getDateAnniversary()))")
    fun toResponseUser(users: Users): UsersResponse
}
