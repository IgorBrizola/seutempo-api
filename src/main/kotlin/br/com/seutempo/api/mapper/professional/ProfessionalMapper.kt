package br.com.seutempo.api.mapper.professional

import br.com.seutempo.api.model.professional.Professional
import br.com.seutempo.api.model.professional.request.UsersProfessionalRequestNew
import br.com.seutempo.api.model.professional.response.ProfessionalResponse
import br.com.seutempo.api.model.specialty.Specialty
import br.com.seutempo.api.model.specialty.response.SpecialtyResponse
import br.com.seutempo.api.model.users.Users
import br.com.seutempo.api.model.users.response.UsersResponse
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
    fun professionalToNewUsersProfessionalRequest(professional: Professional): UsersProfessionalRequestNew

    @Mapping(source = "user", target = "user")
    @Mapping(source = "newUsersProfessionalRequest.valueHour", target = "valueHour")
    @Mapping(source = "linkProfessional", target = "linkProfessional")
    @Mapping(source = "specialties", target = "specialties")
    fun newUsersProfessionalRequestToProfessional(
        user: Users,
        newUsersProfessionalRequest: UsersProfessionalRequestNew,
        lat: Double,
        lon: Double,
        location: Point,
        linkProfessional: String,
        specialties: List<Specialty>,
    ): Professional

    @Mapping(source = "specialties", target = "specialties")
    fun professionalToProfessionalResponse(
        user: UsersResponse,
        professional: Professional,
        specialties: List<SpecialtyResponse>,
    ): ProfessionalResponse

    fun professionalResponseToProfessional(professionalResponse: ProfessionalResponse): Professional
}
