package br.com.seutempo.api.model.users.request

import br.com.seutempo.api.model.address.AddressRequest
import java.time.LocalDate

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
)
