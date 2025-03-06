package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.core.domain.model.professional.Professional
import br.com.seutempo.api.core.domain.model.professional.request.UpdateLocation
import java.math.BigDecimal

interface ManageProfessionalInputPort {
    fun createUsersProfessional(professional: Professional)

    fun getProfessionals(
        name: String?,
        maxValue: BigDecimal?,
        minValue: BigDecimal?,
    ): List<Professional>

    fun getProfessionalBySpecialtyId(id: Int): MutableList<Professional>

    fun getProfessionalByCategoryId(id: Int): List<Professional>

    fun findProfessionalWithLocation(id: Int): List<Professional>

    fun findProfessionalById(id: Int): Professional

    fun findProfessionalByLinkName(linkName: String): Professional

    fun updateAddress(updateLocation: UpdateLocation)
}
