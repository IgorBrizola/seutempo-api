package br.com.seutempo.api.adapters.integration

import br.com.seutempo.api.adapters.integration.client.GoogleMapsClient
import br.com.seutempo.api.adapters.web.mapper.googleMaps.GoogleMapper
import br.com.seutempo.api.core.domain.model.googleMaps.response.GeolocationDomainResponse
import br.com.seutempo.api.core.ports.output.ManageGoogleMapsIntegrationOutputPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GoogleMapsIntegration(
    private val googleMapsIntegration: GoogleMapsClient,
    private val googleMapper: GoogleMapper,
) : ManageGoogleMapsIntegrationOutputPort {
    @Value("\${app.service.google.maps.key}")
    lateinit var apiKey: String

    override fun getGeolocationUser(address: String): GeolocationDomainResponse =
        googleMapper.toGeoDomain(
            googleMapsIntegration.getGeolocationUser(address, apiKey),
        )
}
