package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.adapters.web.model.request.professional.UpdateAddressProfessionalRequest
import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse
import br.com.seutempo.api.core.domain.model.professional.Professional
import java.math.BigDecimal

interface ManageProfessionalInputPort {
    fun createUsersProfessional(professional: Professional)

    fun getProfessionalToClients(
        name: String?,
        value: BigDecimal?,
    ): List<ProfessionalResponse>

    fun getProfessionalBySpecialtyId(id: Int): MutableList<Professional>

    fun getProfessionalByCategoryId(id: Int): List<ProfessionalResponse>

    fun findProfessionalWithLocation(id: Int): List<ProfessionalResponse>

    fun findProfessionalById(id: Int): Professional

    fun findProfessionalByLinkName(linkName: String): ProfessionalResponse

    fun updateAddress(
        id: Int,
        updateAddressProfessionalRequest: UpdateAddressProfessionalRequest,
    )
}
