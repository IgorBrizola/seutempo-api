package br.com.seutempo.api.core.domain.model.users.request

import br.com.seutempo.api.adapters.repository.model.TypeUser
import java.time.LocalDate

data class CreateUser(
    val name: String,
    val middleName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val cpf: String,
    val phone: String,
    val photoUser: String? = null,
    val dateAnniversary: LocalDate,
    val typeUser: TypeUser = TypeUser.PROFESSIONAL,
    val active: Boolean = true,
)
