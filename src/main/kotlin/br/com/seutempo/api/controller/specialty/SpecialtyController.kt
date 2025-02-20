package br.com.seutempo.api.controller.specialty

import br.com.seutempo.api.model.specialty.request.SpecialtyNewRequest
import br.com.seutempo.api.model.specialty.response.SpecialtyResponse
import br.com.seutempo.api.service.specialty.SpecialtyService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("specialty")
class SpecialtyController(
    private val specialtyService: SpecialtyService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun createSpecialty(
        @RequestBody specialtyNewRequest: SpecialtyNewRequest,
    ) = specialtyService.createNewSpecialty(specialtyNewRequest)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllSpecialty(): List<SpecialtyResponse> = specialtyService.getAllSpecialty()

    @GetMapping("professional/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getSpecialtyByProfessional(
        @PathVariable id: Int,
    ): List<SpecialtyResponse> = specialtyService.getSpecialtyByProfessional(id)
}
