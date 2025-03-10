package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.core.domain.model.googleMaps.response.AddressResponse

interface ManageGoogleMapsInputPort {
    fun getInfoLocations(cep: String): AddressResponse
}
