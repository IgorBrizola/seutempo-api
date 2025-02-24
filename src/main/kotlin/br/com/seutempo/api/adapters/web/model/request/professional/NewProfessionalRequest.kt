package br.com.seutempo.api.adapters.web.model.request.professional

import br.com.seutempo.api.adapters.repository.model.TypeUser
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class NewProfessionalRequest(
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val cpf: String,
    val phone: String,
    val dateAnniversary: LocalDate,
    val valueHour: BigDecimal,
    val specialtyIds: List<Int>,
    val cep: String,
    val serviceRadiusKm: Int,
    val typeUser: TypeUser = TypeUser.PROFESSIONAL,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val active: Boolean = true,
)
