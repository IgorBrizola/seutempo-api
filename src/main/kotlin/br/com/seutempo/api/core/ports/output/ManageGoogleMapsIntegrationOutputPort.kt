package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.integration.model.response.GeolocationResponse

interface ManageGoogleMapsIntegrationOutputPort {
    fun getGeolocationUser(
        address: String,
        key: String,
    ): GeolocationResponse
}
