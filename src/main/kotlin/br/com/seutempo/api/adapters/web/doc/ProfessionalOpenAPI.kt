package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.adapters.web.model.request.professional.NewProfessionalRequest
import br.com.seutempo.api.adapters.web.model.request.professional.UpdateAddressProfessionalRequest
import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse
import io.swagger.v3.oas.annotations.tags.Tag
import java.math.BigDecimal

@Tag(name = "professional")
interface ProfessionalOpenAPI {
    fun registerUsersProfessional(professionalRequestNew: NewProfessionalRequest)

    fun getProfessionalsToClient(
        name: String? = null,
        value: BigDecimal? = null,
    ): List<ProfessionalResponse>

    fun getProfessionalsWithRadius(id: Int): List<ProfessionalResponse>

    fun updateGeolocation(
        id: Int,
        updateAddressProfessionalRequest: UpdateAddressProfessionalRequest,
    )

    fun getProfessionalBySpecialty(id: Int): List<ProfessionalResponse>

    fun getProfessionalByCategory(id: Int): List<ProfessionalResponse>

    fun findProfessionalById(id: Int): ProfessionalResponse

    fun findProfessionalByLinkName(linkName: String): ProfessionalResponse
}
