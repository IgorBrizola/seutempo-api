package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.repository.model.UsersEntity
import java.util.Optional

interface ManageUsersOutputPort {
    fun existsByEmailAndActiveIsTrue(email: String): Boolean

    fun existsByCpfAndActiveIsTrue(cpf: String): Boolean

    fun findByIdAndActiveIsTrue(id: Int): Optional<UsersEntity>

    fun findAllByActiveIsTrue(): List<UsersEntity>

    fun findByEmailAndActiveIsTrue(email: String): UsersEntity?

    fun save(usersEntity: UsersEntity): UsersEntity

    fun findById(id: Int): Optional<UsersEntity>
}
