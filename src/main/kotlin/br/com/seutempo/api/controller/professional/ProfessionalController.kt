package br.com.seutempo.api.controller.professional

import br.com.seutempo.api.integration.GoogleMapsIntegration
import br.com.seutempo.api.model.professional.request.UsersProfessionalRequestNew
import br.com.seutempo.api.model.professional.response.ProfessionalResponse
import br.com.seutempo.api.service.professional.ProfessionalService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("professional")
class ProfessionalController(
    private val professionalService: ProfessionalService,
    private val googleMapsIntegration: GoogleMapsIntegration,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUsersProfessional(
        @RequestBody professionalRequestNew: UsersProfessionalRequestNew,
    ) = professionalService.createUsersProfessional(professionalRequestNew)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getProfessionalsToClient(
        @RequestParam("name") name: String? = null,
    ): List<ProfessionalResponse> = professionalService.getProfessionalToClients(name)

    @GetMapping("location/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getProfessionalsWithRadius(
        @PathVariable id: Int,
    ): List<ProfessionalResponse> = professionalService.findProfessionalWithLocation(id)

    @GetMapping("specialty/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getProfessionalBySpecialty(
        @PathVariable id: Int,
    ): List<ProfessionalResponse> = professionalService.getProfessionalBySpecialtyId(id)

    @GetMapping("category/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getProfessionalByCategory(
        @PathVariable id: Int,
    ): List<ProfessionalResponse> = professionalService.getProfessionalByCategoryId(id)

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findProfessionalById(
        @PathVariable id: Int,
    ) = professionalService.findProfessionalById(id)

    @GetMapping("st")
    @ResponseStatus(HttpStatus.OK)
    fun findProfessionalByLinkName(
        @RequestParam linkName: String,
    ) = professionalService.findProfessionalByLinkName(linkName)
}
