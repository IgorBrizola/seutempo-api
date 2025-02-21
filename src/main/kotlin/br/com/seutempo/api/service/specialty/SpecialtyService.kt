package br.com.seutempo.api.service.specialty

import br.com.seutempo.api.mapper.specialty.SpecialtyMapper
import br.com.seutempo.api.model.exception.ResourceAlreadyExistsException
import br.com.seutempo.api.model.exception.ResourceNotFoundException
import br.com.seutempo.api.model.specialty.Specialty
import br.com.seutempo.api.model.specialty.request.SpecialtyNewRequest
import br.com.seutempo.api.model.specialty.request.UpdateSpecialtyRequest
import br.com.seutempo.api.model.specialty.response.SpecialtyResponse
import br.com.seutempo.api.repository.category.CategoryRepository
import br.com.seutempo.api.repository.professional.ProfessionalRepository
import br.com.seutempo.api.repository.specialty.SpecialtyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SpecialtyService(
    private val specialtyRepository: SpecialtyRepository,
    private val categoryRepository: CategoryRepository,
    private val professionalRepository: ProfessionalRepository,
    private val specialtyMapper: SpecialtyMapper,
) {
    @Transactional
    fun createNewSpecialty(specialtyNewRequest: SpecialtyNewRequest) {
        val category =
            categoryRepository
                .findById(
                    specialtyNewRequest.categoryId,
                ).orElseThrow { ResourceNotFoundException("Category not found!") }

        if (specialtyRepository.existsByNameSpecialtyAndCategoryId(specialtyNewRequest.name, specialtyNewRequest.categoryId)) {
            throw ResourceAlreadyExistsException(
                "Specialty (specialty - ${specialtyNewRequest.name}) already exist to is category",
            )
        }

        specialtyRepository.save(
            Specialty(
                nameSpecialty = specialtyNewRequest.name,
                category = category,
            ),
        )
    }

    fun findSpecialtyByIds(specialtyIds: List<Int>): List<SpecialtyResponse> =
        specialtyRepository.findAllById(specialtyIds).map { item -> specialtyMapper.toSpecialtyResponse(item) }

    fun getAllSpecialty(): List<SpecialtyResponse> =
        specialtyRepository
            .findAll()
            .map { item -> SpecialtyResponse(item.category.nameCategory, item.nameSpecialty) }

    fun getSpecialtyByProfessional(id: Int?): List<SpecialtyResponse> =
        specialtyRepository
            .findByProfessionalsId(
                id,
            ).map { item -> SpecialtyResponse(nameSpecialty = item.nameSpecialty, nameCategory = item.category.nameCategory) }

    fun deleteSpecialtyById(id: Int) {
        val specialty =
            specialtyRepository
                .findById(id)
                .orElseThrow {
                    ResourceNotFoundException("Specialty not found! - $id")
                }
        specialty.professionals.forEach { it.specialties.remove(specialty) }

        professionalRepository.saveAll(specialty.professionals)

        specialtyRepository.deleteById(id)
    }

    fun updateSpecialty(
        id: Int,
        updateSpecialtyRequest: UpdateSpecialtyRequest,
    ) {
        val specialty = specialtyRepository.findById(id).orElseThrow { ResourceNotFoundException("Specialty not found! - $id") }
        val category =
            categoryRepository
                .findById(
                    updateSpecialtyRequest.categoryId ?: specialty.category.id!!,
                ).orElseThrow { ResourceNotFoundException("Category not found! - $id") }
        val specialtyUpdate =
            specialty.copy(
                nameSpecialty = updateSpecialtyRequest.nameSpecialty ?: specialty.nameSpecialty,
                category = category,
            )
        specialtyRepository.save(specialtyUpdate)
    }
}
