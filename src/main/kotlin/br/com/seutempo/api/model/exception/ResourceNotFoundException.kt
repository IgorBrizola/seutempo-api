package br.com.seutempo.api.model.exception

class ResourceNotFoundException(
    message: String,
) : RuntimeException(message)
