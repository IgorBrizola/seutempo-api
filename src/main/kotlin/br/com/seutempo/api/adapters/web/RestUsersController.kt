package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.UsersOpenAPI
import br.com.seutempo.api.adapters.web.mapper.users.UsersMapper
import br.com.seutempo.api.adapters.web.model.response.users.UsersResponse
import br.com.seutempo.api.core.ports.input.ManageUsersInputPort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class RestUsersController(
    private val manageUsersUseCase: ManageUsersInputPort,
    private val usersMapper: UsersMapper,
) : UsersOpenAPI {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun findAllUsers(): List<UsersResponse> {
        val users = manageUsersUseCase.getUsers()
        return usersMapper.toListUsersResponse(users)
    }
}
