package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.ProfessionalOpenAPI
import br.com.seutempo.api.adapters.web.model.request.professional.NewProfessionalRequest
import br.com.seutempo.api.adapters.web.model.request.professional.UpdateAddressProfessionalRequest
import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse
import br.com.seutempo.api.core.ports.input.ManageProfessionalInputPort
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
    private val manageProfessionalUseCase: ManageProfessionalInputPort,
) : ProfessionalOpenAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun registerUsersProfessional(
        @RequestBody professionalRequestNew: NewProfessionalRequest,
    ) = manageProfessionalUseCase.createUsersProfessional(professionalRequestNew)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun getProfessionalsToClient(
        @RequestParam("name") name: String?,
        @RequestParam("value") value: BigDecimal?,
    ): List<ProfessionalResponse> = manageProfessionalUseCase.getProfessionalToClients(name, value)

    @GetMapping("location/{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun getProfessionalsWithRadius(
        @PathVariable id: Int,
    ): List<ProfessionalResponse> = manageProfessionalUseCase.findProfessionalWithLocation(id)

    @PutMapping("location/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun updateGeolocation(
        @PathVariable id: Int,
        @RequestBody updateAddressProfessionalRequest: UpdateAddressProfessionalRequest,
    ) = manageProfessionalUseCase.updateAddress(id, updateAddressProfessionalRequest)

    @GetMapping("specialty/{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun getProfessionalBySpecialty(
        @PathVariable id: Int,
    ): List<ProfessionalResponse> = manageProfessionalUseCase.getProfessionalBySpecialtyId(id)

    @GetMapping("category/{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun getProfessionalByCategory(
        @PathVariable id: Int,
    ): List<ProfessionalResponse> = manageProfessionalUseCase.getProfessionalByCategoryId(id)

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun findProfessionalById(
        @PathVariable id: Int,
    ) = manageProfessionalUseCase.findProfessionalById(id)

    @GetMapping("st")
    @ResponseStatus(HttpStatus.OK)
    override fun findProfessionalByLinkName(
        @RequestParam linkName: String,
    ) = manageProfessionalUseCase.findProfessionalByLinkName(linkName)
}
