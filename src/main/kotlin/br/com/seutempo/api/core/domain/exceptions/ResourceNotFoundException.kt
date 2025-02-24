package br.com.seutempo.api.core.domain.exceptions

class ResourceNotFoundException(
    message: String,
) : RuntimeException(message)
