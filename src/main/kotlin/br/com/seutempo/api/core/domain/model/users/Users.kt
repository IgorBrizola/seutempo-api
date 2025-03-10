package br.com.seutempo.api.core.domain.model.users

import br.com.seutempo.api.adapters.repository.model.TypeUser
import java.time.LocalDate
import java.time.LocalDateTime

data class Users(
    val id: Int?,
    val name: String,
    val middleName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val cpf: String,
    val phone: String,
    val photoUser: String?,
    val dateAnniversary: LocalDate,
    val createdAt: LocalDateTime,
    val typeUser: TypeUser,
    val active: Boolean,
)
