package br.com.seutempo.api.adapters.integration

import br.com.seutempo.api.adapters.integration.client.GoogleMapsClient
import br.com.seutempo.api.adapters.web.mapper.googleMaps.GoogleMapper
import br.com.seutempo.api.core.domain.model.googleMaps.response.GeolocationDomainResponse
import br.com.seutempo.api.core.ports.output.ManageGoogleMapsIntegrationOutputPort
import org.springframework.stereotype.Service

@Service
class GoogleMapsIntegration(
    private val googleMapsIntegration: GoogleMapsClient,
    private val googleMapper: GoogleMapper,
) : ManageGoogleMapsIntegrationOutputPort {
    override fun getGeolocationUser(
        address: String,
        key: String,
    ): GeolocationDomainResponse =
        googleMapper.toGeoDomain(
            googleMapsIntegration.getGeolocationUser(address, key),
        )
}
