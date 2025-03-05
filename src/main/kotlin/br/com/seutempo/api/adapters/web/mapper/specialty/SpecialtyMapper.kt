package br.com.seutempo.api.adapters.web.mapper.specialty

import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity
import br.com.seutempo.api.adapters.web.model.request.specialty.NewSpecialtyRequest
import br.com.seutempo.api.adapters.web.model.request.specialty.UpdateSpecialtyRequest
import br.com.seutempo.api.adapters.web.model.response.specialty.SpecialtyResponse
import br.com.seutempo.api.core.domain.model.category.Category
import br.com.seutempo.api.core.domain.model.specialty.Specialty
import br.com.seutempo.api.core.domain.model.specialty.request.CreateSpecialty
import br.com.seutempo.api.core.domain.model.specialty.request.UpdateSpecialty
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
    @Mapping(source = "professionalEntities", target = "professionals")
    @Mapping(target = "id", ignore = true)
    fun toSpecialty(specialtyEntity: SpecialtyEntity): Specialty

    @Mapping(source = "category", target = "categoryEntity")
    @Mapping(source = "category.categoryId", target = "categoryEntity.id")
    @Mapping(source = "professionals", target = "professionalEntities")
    fun toEntity(specialty: Specialty): SpecialtyEntity

    @Mapping(source = "category.nameCategory", target = "nameCategory")
    fun toResponse(specialty: Specialty): SpecialtyResponse

    fun toListSpecialty(specialty: List<SpecialtyEntity>): List<Specialty>

    fun toListSpecialtyResponse(specialty: List<Specialty>): List<SpecialtyResponse>

    @Mapping(source = "categoryEntity.nameCategory", target = "nameCategory")
    fun toSpecialtyResponse(specialtyEntity: SpecialtyEntity): SpecialtyResponse

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
    @Mapping(target = "id", ignore = true)
    fun createToDomain(
        createSpecialty: CreateSpecialty,
        category: Category,
    ): Specialty
}
