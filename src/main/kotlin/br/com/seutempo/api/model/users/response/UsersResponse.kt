package br.com.seutempo.api.model.users.response

import br.com.seutempo.api.model.users.TypeUser

data class UsersResponse(
    val id: Int?,
    val name: String?,
    val email: String?,
    val phone: String?,
    val age: Int?,
    val typeUser: TypeUser?,
)
