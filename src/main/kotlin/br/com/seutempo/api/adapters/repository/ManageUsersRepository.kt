package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.users.UsersJpaRepository
import br.com.seutempo.api.adapters.repository.model.UsersEntity
import br.com.seutempo.api.core.ports.output.ManageUsersOutputPort
import java.util.Optional

class ManageUsersRepository(
    private val usersJpaRepository: UsersJpaRepository,
) : ManageUsersOutputPort {
    override fun existsByEmailAndActiveIsTrue(email: String): Boolean = usersJpaRepository.existsByEmailAndActiveIsTrue(email)

    override fun existsByCpfAndActiveIsTrue(cpf: String): Boolean = usersJpaRepository.existsByCpfAndActiveIsTrue(cpf)

    override fun findByIdAndActiveIsTrue(id: Int): Optional<UsersEntity> = usersJpaRepository.findByIdAndActiveIsTrue(id)

    override fun findAllByActiveIsTrue(): List<UsersEntity> = usersJpaRepository.findAllByActiveIsTrue()

    override fun findByEmailAndActiveIsTrue(email: String): UsersEntity? = usersJpaRepository.findByEmailAndActiveIsTrue(email)

    override fun save(usersEntity: UsersEntity): UsersEntity = usersJpaRepository.save(usersEntity)

    override fun findById(id: Int): Optional<UsersEntity> = usersJpaRepository.findById(id)

    override fun findAll(): List<UsersEntity> = usersJpaRepository.findAll()
}
