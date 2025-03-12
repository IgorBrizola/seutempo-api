package br.com.seutempo.api.adapters.web.model.request.client

import br.com.seutempo.api.adapters.repository.model.TypeUser
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalDateTime

data class NewClientRequest(
    @Schema(defaultValue = "Thauanny")
    val name: String,
    @Schema(defaultValue = "Mayumi")
    val middleName: String,
    @Schema(defaultValue = "Suzuki")
    val lastName: String,
    @Schema(defaultValue = "thau@gmail.com")
    val email: String,
    @Schema(defaultValue = "123")
    val password: String,
    @Schema(defaultValue = "https://")
    val photoUser: String? = null,
    @Schema(defaultValue = "477.444.333-23")
    val cpf: String,
    @Schema(defaultValue = "+55 (11) 93982-5593")
    val phone: String,
    @Schema(defaultValue = "2006-03-09")
    val dateAnniversary: LocalDate,
    @Schema(defaultValue = "thau")
    val surname: String?,
    val address: AddressClientRequest,
    @Schema(hidden = true, defaultValue = "CLIENT")
    val typeUser: TypeUser = TypeUser.CLIENT,
    @Schema(hidden = true)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Schema(hidden = true)
    val active: Boolean = true,
)
