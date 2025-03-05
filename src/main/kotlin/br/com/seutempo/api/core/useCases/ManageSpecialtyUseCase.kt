package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity
import br.com.seutempo.api.adapters.web.mapper.specialty.SpecialtyMapper
import br.com.seutempo.api.adapters.web.model.request.specialty.NewSpecialtyRequest
import br.com.seutempo.api.adapters.web.model.request.specialty.UpdateSpecialtyRequest
import br.com.seutempo.api.adapters.web.model.response.specialty.SpecialtyResponse
import br.com.seutempo.api.core.domain.exceptions.ResourceAlreadyExistsException
import br.com.seutempo.api.core.domain.exceptions.ResourceNotFoundException
import br.com.seutempo.api.core.ports.input.ManageSpecialtyInputPort
import br.com.seutempo.api.core.ports.output.ManageCategoryOutputPort
import br.com.seutempo.api.core.ports.output.ManageProfessionalOutputPort
import br.com.seutempo.api.core.ports.output.ManageSpecialtyOutputPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManageSpecialtyUseCase(
    private val specialtyJpaRepository: ManageSpecialtyOutputPort,
    private val categoryJpaRepository: ManageCategoryOutputPort,
    private val professionalJpaRepository: ManageProfessionalOutputPort,
    private val specialtyMapper: SpecialtyMapper,
) : ManageSpecialtyInputPort {
    @Transactional
    override fun createNewSpecialty(newSpecialtyRequest: NewSpecialtyRequest) {
        val category =
            categoryJpaRepository
                .findById(
                    newSpecialtyRequest.categoryId,
                ).orElseThrow { ResourceNotFoundException("Category not found!") }

        if (specialtyJpaRepository.existsByNameSpecialtyAndCategoryEntityId(newSpecialtyRequest.name, newSpecialtyRequest.categoryId)) {
            throw ResourceAlreadyExistsException(
                "Specialty (specialty - ${newSpecialtyRequest.name}) already exist to is category",
            )
        }

        specialtyJpaRepository.save(
            SpecialtyEntity(
                nameSpecialty = newSpecialtyRequest.name,
                categoryEntity = category,
            ),
        )
    }

    override fun findSpecialtyByIds(specialtyIds: List<Int>): List<SpecialtyResponse> =
        specialtyJpaRepository.findAllById(specialtyIds).map { item -> specialtyMapper.toSpecialtyResponse(item) }

    override fun findSpecialtyRegisterProfessional(specialtyIds: List<Int>): List<SpecialtyEntity> =
        specialtyJpaRepository.findAllById(specialtyIds)

    override fun getAllSpecialty(): List<SpecialtyResponse> =
        specialtyJpaRepository
            .findAll()
            .map { item -> SpecialtyResponse(item.categoryEntity.nameCategory, item.nameSpecialty) }

    override fun getSpecialtyByProfessional(id: Int?): List<SpecialtyResponse> =
        specialtyJpaRepository
            .findByProfessionalEntitiesId(
                id,
            ).map { item -> SpecialtyResponse(nameSpecialty = item.nameSpecialty, nameCategory = item.categoryEntity.nameCategory) }

    override fun deleteSpecialtyById(id: Int) {
        val specialty =
            specialtyJpaRepository
                .findById(id)
                .orElseThrow {
                    ResourceNotFoundException("Specialty not found! - $id")
                }
        specialty.professionalEntities.forEach { it.specialties.remove(specialty) }

        professionalJpaRepository.saveAll(specialty.professionalEntities)

        specialtyJpaRepository.deleteById(id)
    }

    override fun updateSpecialty(
        id: Int,
        updateSpecialtyRequest: UpdateSpecialtyRequest,
    ) {
        val specialty = specialtyJpaRepository.findById(id).orElseThrow { ResourceNotFoundException("Specialty not found! - $id") }
        val category =
            categoryJpaRepository
                .findById(
                    updateSpecialtyRequest.categoryId ?: specialty.categoryEntity.id!!,
                ).orElseThrow { ResourceNotFoundException("Category not found! - $id") }
        val specialtyUpdate =
            specialty.copy(
                nameSpecialty = updateSpecialtyRequest.nameSpecialty ?: specialty.nameSpecialty,
                categoryEntity = category,
            )
        specialtyJpaRepository.save(specialtyUpdate)
    }
}
