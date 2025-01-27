package br.com.seutempo.api.controller.users

import br.com.seutempo.api.model.users.request.UsersRequestNew
import br.com.seutempo.api.model.users.response.UsersResponse
import br.com.seutempo.api.service.users.UsersService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UsersController(
    private val usersService: UsersService,
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAllUsers(): List<UsersResponse> = usersService.getUsers()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUsers(
        @RequestBody usersRequestNew: UsersRequestNew,
    ) = usersService.createUsers(usersRequestNew)
}
