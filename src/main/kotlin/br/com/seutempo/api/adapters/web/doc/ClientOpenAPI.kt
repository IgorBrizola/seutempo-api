package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.adapters.web.model.request.client.NewClientRequest
import br.com.seutempo.api.adapters.web.model.request.client.UpdateAddressClientRequest
import br.com.seutempo.api.adapters.web.model.request.client.UpdateClientRequest
import br.com.seutempo.api.adapters.web.model.response.client.ClientResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "client controller")
interface ClientOpenAPI {
    @Operation(summary = "Create new client")
    fun registerUsersClient(newClientRequest: NewClientRequest)

    @Operation(summary = "List all clients")
    fun listAllClients(): List<ClientResponse>

    @Operation(summary = "List client by id")
    fun listClientById(id: Int): ClientResponse

    @Operation(summary = "Update information basic client")
    fun updateClient(
        id: Int,
        updateClientRequest: UpdateClientRequest,
    ): ClientResponse

    @Operation(summary = "Update address client")
    fun updateAddress(
        id: Int,
        updateAddressClientRequest: UpdateAddressClientRequest,
    ): ClientResponse
}
