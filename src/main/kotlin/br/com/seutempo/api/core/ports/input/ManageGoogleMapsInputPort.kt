package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.core.domain.model.googleMaps.response.GeolocationDomainResponse

interface ManageGoogleMapsInputPort {
    fun getInfoLocations(cep: String): GeolocationDomainResponse
}
