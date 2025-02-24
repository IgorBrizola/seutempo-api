package br.com.seutempo.api.adapters.web.model.response.users

import br.com.seutempo.api.adapters.repository.model.TypeUser
import java.time.LocalDate
import java.time.Period

data class UsersResponse(
    val id: Int?,
    val name: String,
    val email: String,
    val phone: String,
    val dateAnniversary: LocalDate,
    val typeUser: TypeUser,
) {
    val age: Int = calcAge(dateAnniversary)

    companion object {
        fun calcAge(dateAnniversary: LocalDate): Int = Period.between(dateAnniversary, LocalDate.now()).years
    }
}
