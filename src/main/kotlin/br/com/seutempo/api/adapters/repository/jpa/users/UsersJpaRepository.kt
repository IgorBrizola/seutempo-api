package br.com.seutempo.api.adapters.repository.jpa.users

import br.com.seutempo.api.adapters.repository.model.UsersEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsersJpaRepository : JpaRepository<UsersEntity, Int> {
    fun existsByEmailAndActiveIsTrue(email: String): Boolean

    fun existsByCpfAndActiveIsTrue(cpf: String): Boolean

    fun findByIdAndActiveIsTrue(id: Int): Optional<UsersEntity>

    fun findAllByActiveIsTrue(): List<UsersEntity>

    fun findByEmailAndActiveIsTrue(email: String): UsersEntity?
}
