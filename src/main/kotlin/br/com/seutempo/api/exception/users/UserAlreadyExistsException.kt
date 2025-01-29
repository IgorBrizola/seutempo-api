package br.com.seutempo.api.exception.users

class UserAlreadyExistsException(
    message: String,
) : RuntimeException(message)
