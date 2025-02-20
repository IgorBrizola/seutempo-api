package br.com.seutempo.api.integration.request

data class GeolocationRequest(
    val cep: String,
    val key: String,
)
