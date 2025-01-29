package br.com.seutempo.api.mapper.specialty

import br.com.seutempo.api.model.specialty.Specialty
import br.com.seutempo.api.model.specialty.request.SpecialtyNewRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface SpecialtyMapper {
    fun specialtyToSpecialtyNewRequest(specialty: Specialty): SpecialtyNewRequest

    @Mapping(source = "specialtyNewRequest.name", target = "nameSpecialty")
    fun specialtyNewRequestToSpecialty(specialtyNewRequest: SpecialtyNewRequest): Specialty
}
