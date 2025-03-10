package br.com.seutempo.api.adapters.web.model.request.professional

import br.com.seutempo.api.adapters.repository.model.TypeUser
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class NewProfessionalRequest(
    @Schema(defaultValue = "Igor")
    val name: String,
    @Schema(defaultValue = "Rodrigues")
    val middleName: String,
    @Schema(defaultValue = "Brizola")
    val lastName: String,
    @Schema(defaultValue = "igor@seutempo.com")
    val email: String,
    @Schema(defaultValue = "123")
    val password: String,
    @Schema(defaultValue = "540.345.455-55")
    val cpf: String,
    @Schema(defaultValue = "11 95059-2234")
    val phone: String,
    @Schema(defaultValue = "https://")
    val photoUser: String? = null,
    @Schema(defaultValue = "2005-12-23")
    val dateAnniversary: LocalDate,
    @Schema(defaultValue = "34.50")
    val valueHour: BigDecimal,
    @Schema(defaultValue = "[1,2]")
    val specialtyIds: List<Int>,
    @Schema(defaultValue = "02858-020")
    val cep: String,
    @Schema(defaultValue = "9")
    val serviceRadiusKm: Int,
    @Schema(hidden = true)
    val typeUser: TypeUser = TypeUser.PROFESSIONAL,
    @Schema(hidden = true)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Schema(hidden = true)
    val active: Boolean = true,
)
