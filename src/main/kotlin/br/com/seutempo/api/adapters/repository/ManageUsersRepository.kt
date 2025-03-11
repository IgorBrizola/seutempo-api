package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.users.UsersJpaRepository
import br.com.seutempo.api.adapters.repository.model.UsersEntity
import br.com.seutempo.api.adapters.web.mapper.users.UsersMapper
import br.com.seutempo.api.core.domain.exceptions.ResourceNotFoundException
import br.com.seutempo.api.core.domain.model.users.Users
import br.com.seutempo.api.core.domain.model.users.request.UpdatePasswordInput
import br.com.seutempo.api.core.ports.output.ManageUsersOutputPort
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
class ManageUsersRepository(
    private val usersJpaRepository: UsersJpaRepository,
    private val usersMapper: UsersMapper,
) : ManageUsersOutputPort {
    private val log = LogManager.getLogger(javaClass)

    override fun existsByEmailAndActiveIsTrue(email: String): Boolean = usersJpaRepository.existsByEmailAndActiveIsTrue(email)

    override fun existsByCpfAndActiveIsTrue(cpf: String): Boolean = usersJpaRepository.existsByCpfAndActiveIsTrue(cpf)

    override fun existsByPhoneAndActiveIsTrue(phone: String): Boolean = usersJpaRepository.existsByPhoneAndActiveIsTrue(phone)

    override fun findByIdAndActiveIsTrue(id: Int): Optional<UsersEntity> = usersJpaRepository.findByIdAndActiveIsTrue(id)

    override fun findAllByActiveIsTrue(): List<UsersEntity> = usersJpaRepository.findAllByActiveIsTrue()

    override fun findByEmailAndActiveIsTrue(email: String): UsersEntity? = usersJpaRepository.findByEmailAndActiveIsTrue(email)

    override fun save(user: Users): Users {
        val userEntity = usersMapper.toUserEntity(user)
        return usersMapper.toUsers(usersJpaRepository.save(userEntity))
    }

    override fun findById(id: Int): Users =
        usersMapper.toUsers(
            usersJpaRepository.findById(id).orElseThrow {
                throw ResourceNotFoundException("User not found! - $id")
            },
        )

    override fun findAll(): List<Users> = usersMapper.toListUsers(usersJpaRepository.findAll().filter { it.active == true })

    override fun updatePassword(
        user: Users,
        passwordInput: UpdatePasswordInput,
    ): Users {
        val userEntity = usersMapper.toUserEntity(user)

        userEntity.password = passwordInput.password

        return usersMapper.toUsers(usersJpaRepository.save(userEntity))
    }

    override fun disableUsers(user: Users) {
        val userEntity = usersMapper.toUserEntity(user)
        userEntity.active = false

        log.info("Disable user - ${user.id}")
        usersJpaRepository.save(userEntity)
    }

    override fun activeUsers(user: Users) {
        val userEntity = usersMapper.toUserEntity(user)
        userEntity.active = true

        log.info("Active user - ${user.id}")
        usersJpaRepository.save(userEntity)
    }

    override fun findByIdActive(id: Int): Users =
        usersMapper.toUsers(
            usersJpaRepository.findByIdAndActiveIsTrue(id).orElseThrow {
                throw ResourceNotFoundException("user not found! - $id")
            },
        )
}
