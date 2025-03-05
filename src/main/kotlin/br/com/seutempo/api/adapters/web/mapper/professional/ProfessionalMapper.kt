package br.com.seutempo.api.adapters.web.mapper.professional

import br.com.seutempo.api.adapters.repository.model.ProfessionalEntity
import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity
import br.com.seutempo.api.adapters.repository.model.UsersEntity
import br.com.seutempo.api.adapters.web.model.request.professional.NewProfessionalRequest
import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse
import br.com.seutempo.api.adapters.web.model.response.specialty.SpecialtyResponse
import br.com.seutempo.api.core.domain.model.professional.Professional
import br.com.seutempo.api.core.domain.model.specialty.Specialty
import br.com.seutempo.api.core.domain.model.users.Users
import org.locationtech.jts.geom.Point
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface ProfessionalMapper {
    fun professionalToNewUsersProfessionalRequest(professionalEntity: ProfessionalEntity): NewProfessionalRequest

    fun toEntity(professional: Professional): ProfessionalEntity

    fun toDomain(professionalEntity: ProfessionalEntity): Professional

    fun toListEntity(professionals: List<Professional>): List<ProfessionalEntity>

    fun toListDomain(professionalEntity: List<ProfessionalEntity>): MutableList<Professional>

    @Mapping(source = "user", target = "user")
    @Mapping(source = "newUsersProfessionalRequest.valueHour", target = "valueHour")
    @Mapping(source = "linkNameProfessional", target = "linkNameProfessional")
    @Mapping(source = "urlProfessional", target = "urlProfessional")
    @Mapping(source = "specialties", target = "specialties")
    fun newUsersProfessionalRequestToProfessional(
        user: UsersEntity,
        newUsersProfessionalRequest: NewProfessionalRequest,
        lat: Double,
        lon: Double,
        location: Point,
        linkNameProfessional: String,
        urlProfessional: String,
        specialties: List<Specialty>,
    ): Professional

    @Mapping(source = "categoryEntity.nameCategory", target = "nameCategory")
    fun toSpecialtyResponse(specialties: SpecialtyEntity): SpecialtyResponse

    fun toSpecialtyListResponse(specialties: List<SpecialtyEntity>): List<SpecialtyResponse>

    @Mapping(source = "user", target = "user")
    fun professionalToProfessionalResponse(
        user: Users,
        professional: Professional,
    ): ProfessionalResponse

    fun professionalResponseToProfessional(professionalResponse: ProfessionalResponse): ProfessionalEntity
}
