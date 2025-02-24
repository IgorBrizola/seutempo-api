package br.com.seutempo.api.adapters.repository.jpa.users

import br.com.seutempo.api.adapters.repository.model.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsersJpaRepository : JpaRepository<Users, Int> {
    fun existsByEmailAndActiveIsTrue(email: String): Boolean

    fun existsByCpfAndActiveIsTrue(cpf: String): Boolean

    fun findByIdAndActiveIsTrue(id: Int): Optional<Users>

    fun findAllByActiveIsTrue(): List<Users>

    fun findByEmailAndActiveIsTrue(email: String): Users?
}

// TODO: configure outputPort category
