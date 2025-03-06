package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.core.domain.exceptions.ResourceAlreadyExistsException
import br.com.seutempo.api.core.domain.model.specialty.Specialty
import br.com.seutempo.api.core.domain.model.specialty.request.UpdateSpecialty
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
) : ManageSpecialtyInputPort {
    @Transactional
    override fun createNewSpecialty(specialty: Specialty) {
        if (specialtyJpaRepository.existsByNameSpecialtyAndCategoryEntityId(specialty.nameSpecialty, specialty.category.categoryId)) {
            throw ResourceAlreadyExistsException(
                "Specialty (specialty - ${specialty.nameSpecialty}) already exist to is category",
            )
        }

        specialtyJpaRepository.save(specialty)
    }

    override fun findSpecialtyByIds(specialtyIds: List<Int>): List<Specialty> = specialtyJpaRepository.findAllById(specialtyIds)

    override fun findSpecialtyRegisterProfessional(specialtyIds: List<Int>): List<Specialty> =
        specialtyJpaRepository.findAllById(specialtyIds)

    override fun getAllSpecialty(): List<Specialty> =
        specialtyJpaRepository
            .findAll()

    override fun getSpecialtyWithProfessional(id: Int): Specialty =
        specialtyJpaRepository
            .findSpecialtyWithProfessional(
                id,
            )

    override fun deleteSpecialtyById(id: Int) {
        specialtyJpaRepository.deleteById(id)

        professionalJpaRepository.findProfessionalEntityBySpecialtiesId(id).let {
            professionalJpaRepository.saveAll(it)
        }
    }

    override fun updateSpecialty(updateSpecialty: UpdateSpecialty) {
        val specialty = specialtyJpaRepository.findById(updateSpecialty.id)

        val category =
            categoryJpaRepository
                .findById(
                    updateSpecialty.categoryId ?: specialty.category.categoryId,
                )

        val specialtyUpdate =
            specialty.copy(
                id = updateSpecialty.id,
                nameSpecialty = updateSpecialty.nameSpecialty ?: specialty.nameSpecialty,
                category = category,
            )

        specialtyJpaRepository.save(specialtyUpdate)
    }
}
