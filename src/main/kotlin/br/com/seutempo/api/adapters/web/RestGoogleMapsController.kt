package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.GoogleMapsOpenAPI
import br.com.seutempo.api.adapters.web.mapper.googleMaps.GoogleMapper
import br.com.seutempo.api.core.domain.exceptions.ResourceNotFoundException
import br.com.seutempo.api.core.domain.model.googleMaps.response.AddressResponse
import br.com.seutempo.api.core.useCases.ManageGoogleMapsUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("google-maps")
class RestGoogleMapsController(
    private val googleMapsUseCase: ManageGoogleMapsUseCase,
    private val googleMapper: GoogleMapper,
) : GoogleMapsOpenAPI {
    @GetMapping
    override fun getInfoLocation(
        @RequestParam("cep") cep: String,
    ): AddressResponse {
        val address = googleMapsUseCase.getInfoLocations(cep)

        val result = address.results.firstOrNull() ?: throw ResourceNotFoundException("Not found address to cep! - $cep")

        return AddressResponse(
            cep = cep,
            city = result.addressComponents?.find { "administrative_area_level_2" in it.types }?.longName,
            state = result.addressComponents?.find { "administrative_area_level_1" in it.types }?.shortName,
            formatedAddress = result.formattedAddress,
        )
    }
}
