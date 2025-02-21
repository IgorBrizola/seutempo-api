package br.com.seutempo.api.mapper.specialty

import br.com.seutempo.api.model.specialty.Specialty
import br.com.seutempo.api.model.specialty.response.SpecialtyResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface SpecialtyMapper {
    @Mapping(source = "category.nameCategory", target = "nameCategory")
    fun toSpecialtyResponse(specialty: Specialty): SpecialtyResponse

    fun toSpecialtyEntity(specialtyResponse: SpecialtyResponse): Specialty
}
