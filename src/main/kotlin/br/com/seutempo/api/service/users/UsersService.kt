package br.com.seutempo.api.service.users

import br.com.seutempo.api.repository.users.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersService(
    private val usersRepository: UsersRepository,
)
