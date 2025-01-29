package br.com.seutempo.api.controller.client

import br.com.seutempo.api.model.client.request.UsersClientRequestNew
import br.com.seutempo.api.service.client.ClientService
import br.com.seutempo.api.service.users.UsersService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("client")
class ClientController(
    private val clientService: ClientService,
    private val usersService: UsersService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUsersClient(
        @RequestBody clientRequestNew: UsersClientRequestNew,
    ) = clientService.createUsersClient(clientRequestNew)
}
