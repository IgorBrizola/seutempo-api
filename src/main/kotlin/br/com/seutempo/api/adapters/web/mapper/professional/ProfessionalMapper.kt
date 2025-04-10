package br.com.seutempo.api.adapters.web.mapper.professional

import br.com.seutempo.api.adapters.repository.model.ProfessionalEntity
import br.com.seutempo.api.adapters.web.mapper.specialty.SpecialtyMapper
import br.com.seutempo.api.adapters.web.model.request.professional.NewProfessionalRequest
import br.com.seutempo.api.adapters.web.model.request.professional.UpdateAddressProfessionalRequest
import br.com.seutempo.api.adapters.web.model.request.professional.UpdateProfessionalRequest
import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse
import br.com.seutempo.api.core.domain.model.professional.Professional
import br.com.seutempo.api.core.domain.model.professional.request.CreateProfessional
import br.com.seutempo.api.core.domain.model.professional.request.UpdateLocation
import br.com.seutempo.api.core.domain.model.professional.request.UpdateProfessionalInput
import br.com.seutempo.api.core.domain.model.specialty.Specialty
import br.com.seutempo.api.core.domain.model.users.Users
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [SpecialtyMapper::class],
)
interface ProfessionalMapper {
    fun professionalToNewUsersProfessionalRequest(professionalEntity: ProfessionalEntity): NewProfessionalRequest

    @Mapping(source = "user", target = "user")
    fun toEntity(professional: Professional): ProfessionalEntity

    fun toDomain(professionalEntity: ProfessionalEntity): Professional

    fun toListEntity(professionals: List<Professional>): List<ProfessionalEntity>

    fun toResponse(professional: Professional): ProfessionalResponse

    @Mapping(source = "createProfessional.user", target = "user")
    @Mapping(source = "specialties", target = "specialties")
    fun createToProfessional(
        createProfessional: CreateProfessional,
        specialties: List<Specialty>,
    ): Professional

    @Mapping(source = "user", target = "user")
    @Mapping(source = "professional.id", target = "id")
    fun professionalToProfessionalResponse(
        user: Users,
        professional: Professional,
    ): ProfessionalResponse

    @Mapping(source = "name", target = "user.name")
    @Mapping(source = "middleName", target = "user.middleName")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "email", target = "user.email")
    @Mapping(source = "password", target = "user.password")
    @Mapping(source = "cpf", target = "user.cpf")
    @Mapping(source = "phone", target = "user.phone")
    @Mapping(source = "photoUser", target = "user.photoUser")
    @Mapping(source = "dateAnniversary", target = "user.dateAnniversary")
    @Mapping(source = "typeUser", target = "user.typeUser")
    @Mapping(source = "active", target = "user.active")
    fun toCreate(newUsersProfessionalRequest: NewProfessionalRequest): CreateProfessional

    fun toListDomain(professionalEntity: List<ProfessionalEntity>): MutableList<Professional>

    fun toListProfessionalResponse(professional: List<Professional>): List<ProfessionalResponse>

    fun toUpdateLocation(
        updateAddressProfessionalRequest: UpdateAddressProfessionalRequest,
        id: Int,
    ): UpdateLocation

    fun updateRequestToUpdateInput(
        id: Int,
        updateProfessionalRequest: UpdateProfessionalRequest,
    ): UpdateProfessionalInput
}
