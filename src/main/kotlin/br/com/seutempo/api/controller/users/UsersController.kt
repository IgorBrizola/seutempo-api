package br.com.seutempo.api.controller.users

import br.com.seutempo.api.model.users.response.UsersResponse
import br.com.seutempo.api.service.users.UsersService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UsersController(
    private val usersService: UsersService,
) {
    @GetMapping
    fun findAllUsers(): List<UsersResponse> = usersService.getUsers()
}
