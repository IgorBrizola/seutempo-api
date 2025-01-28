package br.com.seutempo.api.model.users.response

import br.com.seutempo.api.model.users.TypeUser
import java.time.LocalDate
import java.time.Period

data class UsersResponse(
    val id: Int?,
    val name: String?,
    val email: String?,
    val phone: String?,
    val age: Int?,
    val typeUser: TypeUser?,
) {
    companion object {
        fun calcAge(dateBirth: LocalDate): Int {
            val age = Period.between(dateBirth, LocalDate.now()).years
            return age
        }
    }
}
