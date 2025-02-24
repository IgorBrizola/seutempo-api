package br.com.seutempo.api.adapters.integration.model.request

data class GeolocationRequest(
    val cep: String,
    val key: String,
)
