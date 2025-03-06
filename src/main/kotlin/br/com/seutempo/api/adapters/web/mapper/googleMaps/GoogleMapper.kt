package br.com.seutempo.api.adapters.web.mapper.googleMaps

import br.com.seutempo.api.adapters.integration.model.response.GeolocationResponse
import br.com.seutempo.api.core.domain.model.googleMaps.response.GeolocationDomainResponse
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface GoogleMapper {
    fun toGeoDomain(geoDomainResponse: GeolocationResponse): GeolocationDomainResponse
}
