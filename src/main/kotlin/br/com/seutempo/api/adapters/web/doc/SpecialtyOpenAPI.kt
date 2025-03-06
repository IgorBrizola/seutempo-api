package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.adapters.web.model.request.specialty.NewSpecialtyRequest
import br.com.seutempo.api.adapters.web.model.request.specialty.UpdateSpecialtyRequest
import br.com.seutempo.api.adapters.web.model.response.specialty.SpecialtyResponse
import br.com.seutempo.api.core.domain.model.specialty.response.SpecialtyProfessionalResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "specialty controller")
interface SpecialtyOpenAPI {
    @Operation(summary = "Create new specialty")
    fun createSpecialty(newSpecialtyRequest: NewSpecialtyRequest)

    @Operation(summary = "Get all specialty")
    fun getAllSpecialty(): List<SpecialtyResponse>

    @Operation(summary = "Get specialty with professional")
    fun getSpecialtyByProfessional(id: Int): SpecialtyProfessionalResponse

    @Operation(summary = "Delete specialty by id")
    fun deleteSpecialtyById(id: Int)

    @Operation(summary = "Update specialty by id")
    fun updateSpecialty(
        id: Int,
        updateSpecialtyRequest: UpdateSpecialtyRequest,
    )

    @Operation(summary = "Get specialty by id")
    fun getSpecialtyByIds(ids: List<Int>): List<SpecialtyResponse>
}
