package br.com.seutempo.api.service.users

import br.com.seutempo.api.mapper.users.UsersMapper
import br.com.seutempo.api.model.users.response.UsersResponse
import br.com.seutempo.api.repository.users.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersService(
    private val usersRepository: UsersRepository,
    private val usersMapper: UsersMapper,
) {
    fun getUsers(): List<UsersResponse> =
        usersRepository
            .findAll()
            .map { user ->
                usersMapper.toResponseUser(user)
            }
}
