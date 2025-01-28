package br.com.seutempo.api.repository.users

import br.com.seutempo.api.model.users.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsersRepository : JpaRepository<Users, Int> {
    fun existsByEmailAndActiveIsTrue(email: String): Boolean

    fun existsByCpfAndActiveIsTrue(cpf: String): Boolean

    fun findByIdAndActiveIsTrue(id: Int): Optional<Users>

    fun findAllByActiveIsTrue(): List<Users>

    fun findByEmailAndActiveIsTrue(email: String): Users?
}
