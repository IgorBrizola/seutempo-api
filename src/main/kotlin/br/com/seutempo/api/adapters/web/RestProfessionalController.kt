package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.integration.GoogleMapsIntegration
import br.com.seutempo.api.adapters.web.model.request.professional.NewProfessionalRequest
import br.com.seutempo.api.adapters.web.model.request.professional.UpdateAddressProfessionalRequest
import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse
import br.com.seutempo.api.core.useCases.ManageProfessionalUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("professional")
class RestProfessionalController(
    private val manageProfessionalUseCase: ManageProfessionalUseCase,
    private val googleMapsIntegration: GoogleMapsIntegration,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUsersProfessional(
        @RequestBody professionalRequestNew: NewProfessionalRequest,
    ) = manageProfessionalUseCase.createUsersProfessional(professionalRequestNew)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getProfessionalsToClient(
        @RequestParam("name") name: String? = null,
        @RequestParam("value") value: BigDecimal? = null,
    ): List<ProfessionalResponse> = manageProfessionalUseCase.getProfessionalToClients(name, value)

    @GetMapping("location/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getProfessionalsWithRadius(
        @PathVariable id: Int,
    ): List<ProfessionalResponse> = manageProfessionalUseCase.findProfessionalWithLocation(id)

    @PutMapping("location/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateGeolocation(
        @PathVariable id: Int,
        @RequestBody updateAddressProfessionalRequest: UpdateAddressProfessionalRequest,
    ) = manageProfessionalUseCase.updateAddress(id, updateAddressProfessionalRequest)

    @GetMapping("specialty/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getProfessionalBySpecialty(
        @PathVariable id: Int,
    ): List<ProfessionalResponse> = manageProfessionalUseCase.getProfessionalBySpecialtyId(id)

    @GetMapping("category/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getProfessionalByCategory(
        @PathVariable id: Int,
    ): List<ProfessionalResponse> = manageProfessionalUseCase.getProfessionalByCategoryId(id)

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findProfessionalById(
        @PathVariable id: Int,
    ) = manageProfessionalUseCase.findProfessionalById(id)

    @GetMapping("st")
    @ResponseStatus(HttpStatus.OK)
    fun findProfessionalByLinkName(
        @RequestParam linkName: String,
    ) = manageProfessionalUseCase.findProfessionalByLinkName(linkName)
}

// TODO: configure mapper to controller professional
