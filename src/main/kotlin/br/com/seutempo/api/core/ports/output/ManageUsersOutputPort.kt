package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.repository.model.UsersEntity
import br.com.seutempo.api.core.domain.model.users.Users
import br.com.seutempo.api.core.domain.model.users.request.UpdatePasswordInput
import java.util.Optional

interface ManageUsersOutputPort {
    fun existsByEmailAndActiveIsTrue(email: String): Boolean

    fun existsByCpfAndActiveIsTrue(cpf: String): Boolean

    fun existsByPhoneAndActiveIsTrue(phone: String): Boolean

    fun findByIdAndActiveIsTrue(id: Int): Optional<UsersEntity>

    fun findAllByActiveIsTrue(): List<UsersEntity>

    fun findByEmailAndActiveIsTrue(email: String): UsersEntity?

    fun save(user: Users): Users

    fun findById(id: Int): Users

    fun findAll(): List<Users>

    fun updatePassword(
        user: Users,
        passwordInput: UpdatePasswordInput,
    ): Users

    fun findByIdActive(id: Int): Users

    fun disableUsers(user: Users)

    fun activeUsers(user: Users)
}
