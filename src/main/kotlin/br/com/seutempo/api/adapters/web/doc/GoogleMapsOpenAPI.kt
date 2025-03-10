package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.core.domain.model.googleMaps.response.AddressResponse
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "google Maps")
interface GoogleMapsOpenAPI {
    fun getInfoLocation(cep: String): AddressResponse
}
