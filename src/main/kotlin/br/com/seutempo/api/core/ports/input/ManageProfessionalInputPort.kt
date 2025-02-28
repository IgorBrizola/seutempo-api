package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.adapters.web.model.request.professional.NewProfessionalRequest
import br.com.seutempo.api.adapters.web.model.request.professional.UpdateAddressProfessionalRequest
import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse
import java.math.BigDecimal

interface ManageProfessionalInputPort {
    fun createUsersProfessional(newUsersProfessionalRequest: NewProfessionalRequest)

    fun getProfessionalToClients(
        name: String?,
        value: BigDecimal?,
    ): List<ProfessionalResponse>

    fun getProfessionalBySpecialtyId(id: Int): List<ProfessionalResponse>

    fun getProfessionalByCategoryId(id: Int): List<ProfessionalResponse>

    fun findProfessionalWithLocation(id: Int): List<ProfessionalResponse>

    fun findProfessionalById(id: Int): ProfessionalResponse

    fun findProfessionalByLinkName(linkName: String): ProfessionalResponse

    fun updateAddress(
        id: Int,
        updateAddressProfessionalRequest: UpdateAddressProfessionalRequest,
    )
}
