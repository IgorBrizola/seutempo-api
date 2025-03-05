package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.core.domain.model.specialty.Specialty
import br.com.seutempo.api.core.domain.model.specialty.request.UpdateSpecialty

interface ManageSpecialtyInputPort {
    fun createNewSpecialty(specialty: Specialty)

    fun findSpecialtyByIds(specialtyIds: List<Int>): List<Specialty>

    fun findSpecialtyRegisterProfessional(specialtyIds: List<Int>): List<Specialty>

    fun getAllSpecialty(): List<Specialty>

    fun getSpecialtyByProfessional(id: Int?): List<Specialty>

    fun deleteSpecialtyById(id: Int)

    fun updateSpecialty(updateSpecialty: UpdateSpecialty)
}
