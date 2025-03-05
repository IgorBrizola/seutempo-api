package br.com.seutempo.api.adapters.web.model.request.client

import br.com.seutempo.api.adapters.repository.model.TypeUser
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class NewClientRequest(
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val photoUser: String? = null,
    val cpf: String,
    val phone: String,
    val dateAnniversary: LocalDate,
    val surname: String?,
    val address: AddressClientRequest,
    @Schema(hidden = true, defaultValue = "CLIENT")
    val typeUser: TypeUser = TypeUser.CLIENT,
    @Schema(hidden = true)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Schema(hidden = true)
    val active: Boolean = true,
)
