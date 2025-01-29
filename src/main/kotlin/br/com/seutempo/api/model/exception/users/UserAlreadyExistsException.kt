package br.com.seutempo.api.model.exception.users

class UserAlreadyExistsException(
    message: String,
) : RuntimeException(message)
