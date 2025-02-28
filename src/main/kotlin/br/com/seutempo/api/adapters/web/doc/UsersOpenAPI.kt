package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.adapters.web.model.response.users.UsersResponse
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "users")
interface UsersOpenAPI {
    fun findAllUsers(): List<UsersResponse>
}
