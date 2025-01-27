package br.com.seutempo.api.service.users

import br.com.seutempo.api.model.users.response.UsersResponse
import br.com.seutempo.api.repository.users.UsersRepository
import br.com.seutempo.api.util.AppUtil
import org.springframework.stereotype.Service

@Service
class UsersService(
    private val usersRepository: UsersRepository,
) {
    fun getUsers(): List<UsersResponse> =
        usersRepository
            .findAll()
            .map { user ->
                UsersResponse(
                    id = user.id,
                    name = "${user.name} ${user.lastName}",
                    email = user.email,
                    phone = user.phone,
                    age = AppUtil.calcAge(user.dateAnniversary),
                    typeUser = user.typeUser,
                )
            }
}
