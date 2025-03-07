package br.com.seutempo.api.adapters.web.mapper.specialty

import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity
import br.com.seutempo.api.adapters.web.model.request.specialty.NewSpecialtyRequest
import br.com.seutempo.api.adapters.web.model.request.specialty.UpdateSpecialtyRequest
import br.com.seutempo.api.adapters.web.model.response.specialty.SpecialtyResponse
import br.com.seutempo.api.core.domain.model.category.Category
import br.com.seutempo.api.core.domain.model.specialty.Specialty
import br.com.seutempo.api.core.domain.model.specialty.request.CreateSpecialty
import br.com.seutempo.api.core.domain.model.specialty.request.UpdateSpecialty
import br.com.seutempo.api.core.domain.model.specialty.response.SpecialtyProfessionalResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface SpecialtyMapper {
    @Mapping(source = "categoryEntity", target = "category")
    @Mapping(source = "categoryEntity.id", target = "category.categoryId")
    @Mapping(source = "categoryEntity.nameCategory", target = "category.nameCategory")
    @Mapping(source = "professionalEntities", target = "professionals")
    fun toSpecialty(specialtyEntity: SpecialtyEntity): Specialty

    @Mapping(source = "category", target = "categoryEntity")
    @Mapping(source = "category.categoryId", target = "categoryEntity.id")
    @Mapping(source = "category.nameCategory", target = "categoryEntity.nameCategory")
    fun toEntity(specialty: Specialty): SpecialtyEntity

    @Mapping(source = "categoryEntity.nameCategory", target = "nameCategory")
    fun toSpecialtyResponse(specialtyEntity: SpecialtyEntity): SpecialtyResponse

    @Mapping(source = "category.nameCategory", target = "nameCategory")
    fun toResponse(specialty: Specialty): SpecialtyResponse

    fun toSpecialtyEntity(specialtyResponse: SpecialtyResponse): SpecialtyEntity

    @Mapping(source = "id", target = "id")
    @Mapping(source = "updateSpecialtyRequest.nameSpecialty", target = "nameSpecialty")
    @Mapping(source = "updateSpecialtyRequest.categoryId", target = "categoryId")
    fun toUpdate(
        updateSpecialtyRequest: UpdateSpecialtyRequest,
        id: Int,
    ): UpdateSpecialty

    fun toCreate(newSpecialtyRequest: NewSpecialtyRequest): CreateSpecialty

    fun createToEntity(createSpecialty: CreateSpecialty): SpecialtyEntity

    @Mapping(source = "category", target = "category")
    @Mapping(source = "createSpecialty.nameSpecialty", target = "nameSpecialty")
    fun createToDomain(
        createSpecialty: CreateSpecialty,
        category: Category,
    ): Specialty

    fun toListSpecialty(specialty: MutableList<SpecialtyEntity>): List<Specialty>

    fun toListSpecialtyResponse(specialty: List<Specialty>): List<SpecialtyResponse>

    fun toSpecialtyProfessionalResponse(specialty: Specialty): SpecialtyProfessionalResponse

    fun toListSpecialtyProfessionalResponse(specialty: List<Specialty>): List<SpecialtyProfessionalResponse>
}
