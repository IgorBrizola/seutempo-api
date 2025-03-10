package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.core.domain.model.googleMaps.response.GeolocationDomainResponse

interface ManageGoogleMapsIntegrationOutputPort {
    fun getGeolocationUser(address: String): GeolocationDomainResponse
}
