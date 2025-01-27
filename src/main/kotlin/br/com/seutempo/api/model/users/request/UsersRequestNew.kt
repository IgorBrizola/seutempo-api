package br.com.seutempo.api.model.users.request

import br.com.seutempo.api.model.users.TypeUser
import java.time.LocalDate

data class UsersRequestNew(
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val cpf: String,
    val phone: String,
    val dateAnniversary: LocalDate,
    val typeUser: TypeUser,
)
