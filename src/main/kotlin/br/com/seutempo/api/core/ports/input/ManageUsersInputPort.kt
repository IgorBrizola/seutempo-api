package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.core.domain.model.users.Users
import br.com.seutempo.api.core.domain.model.users.request.UpdatePasswordInput

interface ManageUsersInputPort {
    fun getUsers(): List<Users>

    fun findUserById(id: Int): Users

    fun updatePassword(passwordInput: UpdatePasswordInput)

    fun verifyIfUserIsValid(user: Users)
}
