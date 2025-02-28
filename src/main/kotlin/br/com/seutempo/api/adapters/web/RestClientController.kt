package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.ClientOpenAPI
import br.com.seutempo.api.adapters.web.model.request.client.NewClientRequest
import br.com.seutempo.api.core.useCases.ManageClientUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("client")
class RestClientController(
    private val manageClientUseCase: ManageClientUseCase,
) : ClientOpenAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun registerUsersClient(
        @RequestBody clientRequestNew: NewClientRequest,
    ) = manageClientUseCase.createUsersClient(clientRequestNew)
}
