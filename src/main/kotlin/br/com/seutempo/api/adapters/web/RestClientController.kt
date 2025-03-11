package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.ClientOpenAPI
import br.com.seutempo.api.adapters.web.mapper.client.ClientMapper
import br.com.seutempo.api.adapters.web.model.request.client.NewClientRequest
import br.com.seutempo.api.adapters.web.model.response.client.ClientResponse
import br.com.seutempo.api.core.ports.input.ManageClientInputPort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("client")
class RestClientController(
    private val clientUseCase: ManageClientInputPort,
    private val clientMapper: ClientMapper,
) : ClientOpenAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun registerUsersClient(
        @RequestBody newClientRequest: NewClientRequest,
    ) {
        val createClient = clientMapper.toCrate(newClientRequest)

        val client = clientMapper.createToClient(createClient)

        clientUseCase.createUsersClient(client)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun listAllClients(): List<ClientResponse> =
        clientMapper.toListResponse(
            clientUseCase.listAllClients(),
        )

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun listClientById(
        @PathVariable id: Int,
    ): ClientResponse =
        clientMapper.toResponse(
            clientUseCase.listClientById(id),
        )
}
