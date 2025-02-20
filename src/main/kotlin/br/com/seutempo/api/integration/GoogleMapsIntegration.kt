package br.com.seutempo.api.integration

import br.com.seutempo.api.integration.client.GoogleMapsClient
import br.com.seutempo.api.integration.response.GeolocationResponse
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
