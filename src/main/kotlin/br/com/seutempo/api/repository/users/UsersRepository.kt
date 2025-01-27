package br.com.seutempo.api.repository.users

import br.com.seutempo.api.model.users.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : JpaRepository<Users, Int>
