package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.core.domain.model.googleMaps.response.GeolocationDomainResponse
import br.com.seutempo.api.core.ports.input.ManageGoogleMapsInputPort
import br.com.seutempo.api.core.ports.output.ManageGoogleMapsIntegrationOutputPort
import org.springframework.stereotype.Service

@Service
class ManageGoogleMapsUseCase(
    private val googleMapsIntegrationOutputPort: ManageGoogleMapsIntegrationOutputPort,
) : ManageGoogleMapsInputPort {
    override fun getInfoLocations(cep: String): GeolocationDomainResponse = googleMapsIntegrationOutputPort.getGeolocationUser(cep)
}
