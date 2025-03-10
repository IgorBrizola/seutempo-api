package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.GoogleMapsOpenAPI
import br.com.seutempo.api.adapters.web.mapper.googleMaps.GoogleMapper
import br.com.seutempo.api.core.domain.model.googleMaps.response.AddressResponse
import br.com.seutempo.api.core.ports.input.ManageGoogleMapsInputPort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("google-maps")
class RestGoogleMapsController(
    private val googleMapsUseCase: ManageGoogleMapsInputPort,
    private val googleMapper: GoogleMapper,
) : GoogleMapsOpenAPI {
    @GetMapping
    override fun getInfoLocation(
        @RequestParam("cep") cep: String,
    ): AddressResponse = googleMapsUseCase.getInfoLocations(cep)
}
