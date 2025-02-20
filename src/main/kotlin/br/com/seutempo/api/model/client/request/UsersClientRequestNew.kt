package br.com.seutempo.api.model.client.request

import br.com.seutempo.api.model.users.TypeUser
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class UsersClientRequestNew(
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val cpf: String,
    val phone: String,
    val dateAnniversary: LocalDate,
    val surname: String,
    val address: AddressRequest,
    val typeUser: TypeUser = TypeUser.CLIENT,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val active: Boolean = true,
)
