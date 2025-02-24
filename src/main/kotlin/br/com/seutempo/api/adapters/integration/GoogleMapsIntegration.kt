package br.com.seutempo.api.adapters.integration

import br.com.seutempo.api.adapters.integration.client.GoogleMapsClient
import br.com.seutempo.api.adapters.integration.model.response.GeolocationResponse
import org.springframework.stereotype.Service

@Service
class GoogleMapsIntegration(
    private val googleMapsIntegration: GoogleMapsClient,
) {
    fun getGeolocationUser(
        address: String,
        key: String,
    ): GeolocationResponse = googleMapsIntegration.getGeolocationUser(address, key)
}
