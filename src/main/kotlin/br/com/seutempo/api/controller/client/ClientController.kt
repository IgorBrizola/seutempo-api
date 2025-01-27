package br.com.seutempo.api.controller.client

import br.com.seutempo.api.service.client.ClientService
import br.com.seutempo.api.service.users.UsersService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("client")
class ClientController(
    private val clientService: ClientService,
    private val usersService: UsersService,
)
