package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.adapters.web.model.request.professional.NewProfessionalRequest
import br.com.seutempo.api.adapters.web.model.request.professional.UpdateAddressProfessionalRequest
import br.com.seutempo.api.adapters.web.model.request.professional.UpdateProfessionalRequest
import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import java.math.BigDecimal

@Tag(name = "professional controller")
interface ProfessionalOpenAPI {
    @Operation(summary = "Create new professional")
    fun registerUsersProfessional(newProfessionalRequest: NewProfessionalRequest)

    @Operation(summary = "Get professional to list client")
    fun getProfessionals(
        name: String? = null,
        maxValue: BigDecimal? = null,
        minValue: BigDecimal? = null,
    ): List<ProfessionalResponse>

    @Operation(summary = "Get professional by radius KM")
    fun getProfessionalsWithRadius(id: Int): List<ProfessionalResponse>

    @Operation(summary = "Update CEP and GEOLOCATION")
    fun updateGeolocation(
        id: Int,
        updateAddressProfessionalRequest: UpdateAddressProfessionalRequest,
    ): ProfessionalResponse

    @Operation(summary = "Get professional by specialty")
    fun getProfessionalBySpecialty(id: Int): List<ProfessionalResponse>

    @Operation(summary = "Get professional by category")
    fun getProfessionalByCategory(id: Int): List<ProfessionalResponse>

    @Operation(summary = "Find professional by id")
    fun findProfessionalById(id: Int): ProfessionalResponse

    @Operation(summary = "Find professional by linkName")
    fun findProfessionalByLinkName(linkName: String): ProfessionalResponse

    @Operation(summary = "Update information's basic professional")
    fun updateProfessionalById(
        id: Int,
        updateProfessionalRequest: UpdateProfessionalRequest,
    ): ProfessionalResponse

    @Operation(summary = "Remove specialty professional")
    fun removeSpecialtyProfessional(
        id: Int,
        specialtiesIds: List<Int>,
    )

    @Operation(summary = "Add specialty professional")
    fun addSpecialtyProfessional(
        id: Int,
        specialtiesIds: List<Int>,
    )
}
