package br.com.seutempo.api.service.users

import br.com.seutempo.api.exception.users.UserAlreadyExistsException
import br.com.seutempo.api.model.users.Users
import br.com.seutempo.api.model.users.request.UsersRequestNew
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

    fun createUsers(newUserRequest: UsersRequestNew) {
        val userExist = usersRepository.existsByEmailAndActiveIsTrue(newUserRequest.email)

        if (userExist) throw UserAlreadyExistsException("User with email '${newUserRequest.email}' already exists.")

        val newUser =
            Users(
                name = newUserRequest.name,
                lastName = newUserRequest.lastName,
                email = newUserRequest.email,
                password = newUserRequest.password,
                cpf = newUserRequest.cpf,
                phone = newUserRequest.phone,
                dateAnniversary = newUserRequest.dateAnniversary,
                typeUser = newUserRequest.typeUser,
            )

        usersRepository.save(newUser)
    }
}
