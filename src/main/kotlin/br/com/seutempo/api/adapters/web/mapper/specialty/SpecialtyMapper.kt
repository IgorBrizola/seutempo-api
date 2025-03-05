package br.com.seutempo.api.adapters.web.mapper.specialty

import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity
import br.com.seutempo.api.adapters.web.model.response.specialty.SpecialtyResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface SpecialtyMapper {
    @Mapping(source = "categoryEntity.nameCategory", target = "nameCategory")
    fun toSpecialtyResponse(specialtyEntity: SpecialtyEntity): SpecialtyResponse

    fun toSpecialtyEntity(specialtyResponse: SpecialtyResponse): SpecialtyEntity
}
