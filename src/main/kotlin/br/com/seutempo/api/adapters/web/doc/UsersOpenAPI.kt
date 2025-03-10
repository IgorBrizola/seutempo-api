package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.adapters.web.model.request.user.UpdatePasswordRequest
import br.com.seutempo.api.adapters.web.model.response.users.UsersResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "users controller")
interface UsersOpenAPI {
    @Operation(summary = "Find all users")
    fun findAllUsers(): List<UsersResponse>

    @Operation(summary = "Update password user")
    fun updatePassword(
        id: Int,
        updatePasswordRequest: UpdatePasswordRequest,
    )
}
