package br.com.seutempo.api.model.professional.request

import br.com.seutempo.api.model.users.TypeUser
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class UsersProfessionalRequestNew(
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val cpf: String,
    val phone: String,
    val dateAnniversary: LocalDate,
    val valueHour: BigDecimal,
    val typeUser: TypeUser = TypeUser.PROFESSIONAL,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val active: Boolean = true,
)
