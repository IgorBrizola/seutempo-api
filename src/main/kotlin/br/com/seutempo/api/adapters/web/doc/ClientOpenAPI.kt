package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.adapters.web.model.request.client.NewClientRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "client controller")
interface ClientOpenAPI {
    @Operation(summary = "Create new client")
    fun registerUsersClient(newClientRequest: NewClientRequest)
}
