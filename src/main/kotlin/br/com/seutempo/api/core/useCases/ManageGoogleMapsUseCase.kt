package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.core.domain.exceptions.ResourceNotFoundException
import br.com.seutempo.api.core.domain.model.googleMaps.response.AddressResponse
import br.com.seutempo.api.core.ports.input.ManageGoogleMapsInputPort
import br.com.seutempo.api.core.ports.output.ManageGoogleMapsIntegrationOutputPort
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.PrecisionModel
import org.springframework.stereotype.Service

@Service
class ManageGoogleMapsUseCase(
    private val googleMapsIntegrationOutputPort: ManageGoogleMapsIntegrationOutputPort,
) : ManageGoogleMapsInputPort {
    private val geometryFactory = GeometryFactory(PrecisionModel(), 4326)

    override fun getInfoLocations(cep: String): AddressResponse {
        val address = googleMapsIntegrationOutputPort.getGeolocationUser(cep)

        val result = address.results.firstOrNull() ?: throw ResourceNotFoundException("Not found address to cep! - $cep")

        val location =
            geometryFactory.createPoint(
                org.locationtech.jts.geom
                    .Coordinate(result.geometry.location.lng, result.geometry.location.lat),
            )

        return AddressResponse(
            cep = cep,
            city = result.addressComponents?.find { "administrative_area_level_2" in it.types }?.longName,
            state = result.addressComponents?.find { "administrative_area_level_1" in it.types }?.shortName,
            formatedAddress = result.formattedAddress,
            location = location,
            lon = result.geometry.location.lng,
            lat = result.geometry.location.lat,
        )
    }
}
