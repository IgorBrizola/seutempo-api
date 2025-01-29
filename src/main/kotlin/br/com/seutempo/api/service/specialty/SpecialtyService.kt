package br.com.seutempo.api.service.specialty

import br.com.seutempo.api.model.exception.category.CategoryNotFoundException
import br.com.seutempo.api.model.exception.specialty.SpecialtyAlreadyExistsException
import br.com.seutempo.api.model.specialty.Specialty
import br.com.seutempo.api.model.specialty.request.SpecialtyNewRequest
import br.com.seutempo.api.repository.category.CategoryRepository
import br.com.seutempo.api.repository.specialty.SpecialtyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SpecialtyService(
    private val specialtyRepository: SpecialtyRepository,
    private val categoryRepository: CategoryRepository,
) {
    @Transactional
    fun createNewSpecialty(specialtyNewRequest: SpecialtyNewRequest) {
        val category =
            categoryRepository
                .findById(
                    specialtyNewRequest.categoryId,
                ).orElseThrow { CategoryNotFoundException("Category not found!") }

        if (specialtyRepository.existsByNameSpecialtyAndCategoryId(specialtyNewRequest.name, specialtyNewRequest.categoryId)) {
            throw SpecialtyAlreadyExistsException("Specialty (${specialtyNewRequest.name}) already exist to is category")
        }

        specialtyRepository.save(
            Specialty(
                nameSpecialty = specialtyNewRequest.name,
                category = category,
            ),
        )
    }
}
