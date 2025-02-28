package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity
import br.com.seutempo.api.adapters.web.model.request.specialty.NewSpecialtyRequest
import br.com.seutempo.api.adapters.web.model.request.specialty.UpdateSpecialtyRequest
import br.com.seutempo.api.adapters.web.model.response.specialty.SpecialtyResponse

interface ManageSpecialtyInputPort {
    fun createNewSpecialty(newSpecialtyRequest: NewSpecialtyRequest)

    fun findSpecialtyByIds(specialtyIds: List<Int>): List<SpecialtyResponse>

    fun findSpecialtyRegisterProfessional(specialtyIds: List<Int>): List<SpecialtyEntity>

    fun getAllSpecialty(): List<SpecialtyResponse>

    fun getSpecialtyByProfessional(id: Int?): List<SpecialtyResponse>

    fun deleteSpecialtyById(id: Int)

    fun updateSpecialty(
        id: Int,
        updateSpecialtyRequest: UpdateSpecialtyRequest,
    )
}
