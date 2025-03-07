package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.ProfessionalOpenAPI
import br.com.seutempo.api.adapters.web.mapper.professional.ProfessionalMapper
import br.com.seutempo.api.adapters.web.model.request.professional.NewProfessionalRequest
import br.com.seutempo.api.adapters.web.model.request.professional.UpdateAddressProfessionalRequest
import br.com.seutempo.api.adapters.web.model.request.professional.UpdateProfessionalRequest
import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse
import br.com.seutempo.api.core.ports.input.ManageProfessionalInputPort
import br.com.seutempo.api.core.ports.input.ManageSpecialtyInputPort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
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
    private val manageSpecialtyUseCase: ManageSpecialtyInputPort,
    private val professionalMapper: ProfessionalMapper,
) : ProfessionalOpenAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun registerUsersProfessional(
        @RequestBody newProfessionalRequest: NewProfessionalRequest,
    ) {
        val createProfessional = professionalMapper.toCreate(newProfessionalRequest)

        val specialties =
            manageSpecialtyUseCase
                .findSpecialtyRegisterProfessional(createProfessional.specialtyIds)

        val professional =
            professionalMapper.createToProfessional(
                createProfessional = createProfessional,
                specialties = specialties,
            )

        manageProfessionalUseCase.createUsersProfessional(professional)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun getProfessionals(
        @RequestParam("name") name: String?,
        @RequestParam("maxValue") maxValue: BigDecimal?,
        @RequestParam("minValue") minValue: BigDecimal?,
    ): List<ProfessionalResponse> =
        professionalMapper.toListProfessionalResponse(manageProfessionalUseCase.getProfessionals(name, maxValue, minValue))

    @GetMapping("location/{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun getProfessionalsWithRadius(
        @PathVariable id: Int,
    ): List<ProfessionalResponse> =
        professionalMapper.toListProfessionalResponse(manageProfessionalUseCase.findProfessionalWithLocation(id))

    @PutMapping("location/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun updateGeolocation(
        @PathVariable id: Int,
        @RequestBody updateAddressProfessionalRequest: UpdateAddressProfessionalRequest,
    ): ProfessionalResponse {
        val updateLocation = professionalMapper.toUpdateLocation(updateAddressProfessionalRequest, id)
        return professionalMapper.toResponse(manageProfessionalUseCase.updateAddress(updateLocation))
    }

    @GetMapping("specialty/{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun getProfessionalBySpecialty(
        @PathVariable id: Int,
    ): List<ProfessionalResponse> =
        professionalMapper.toListProfessionalResponse(manageProfessionalUseCase.getProfessionalBySpecialtyId(id))

    @GetMapping("category/{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun getProfessionalByCategory(
        @PathVariable id: Int,
    ): List<ProfessionalResponse> = professionalMapper.toListProfessionalResponse(manageProfessionalUseCase.getProfessionalByCategoryId(id))

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun findProfessionalById(
        @PathVariable id: Int,
    ) = professionalMapper.toResponse(manageProfessionalUseCase.findProfessionalById(id))

    @GetMapping("st")
    @ResponseStatus(HttpStatus.OK)
    override fun findProfessionalByLinkName(
        @RequestParam linkName: String,
    ) = professionalMapper.toResponse(manageProfessionalUseCase.findProfessionalByLinkName(linkName))

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun updateProfessionalById(
        @PathVariable id: Int,
        @RequestBody updateProfessionalRequest: UpdateProfessionalRequest,
    ): ProfessionalResponse {
        val professionalInput = professionalMapper.updateRequestToUpdateInput(id, updateProfessionalRequest)
        return professionalMapper.toResponse(manageProfessionalUseCase.updateProfessionalById(professionalInput))
    }

    @DeleteMapping("disable/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun disableProfessionalById(
        @PathVariable id: Int,
    ) = manageProfessionalUseCase.disableProfessionalById(id)

    @PutMapping("active/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun activeProfessionalById(
        @PathVariable id: Int,
    ) = manageProfessionalUseCase.activeProfessionalById(id)

    @DeleteMapping("specialty/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun removeSpecialtyProfessional(
        @PathVariable id: Int,
        @RequestBody specialtiesIds: List<Int>,
    ) = manageProfessionalUseCase.removeSpecialtyProfessional(id, specialtiesIds)
}
