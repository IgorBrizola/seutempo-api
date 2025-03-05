package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.SpecialtyOpenAPI
import br.com.seutempo.api.adapters.web.model.request.specialty.NewSpecialtyRequest
import br.com.seutempo.api.adapters.web.model.request.specialty.UpdateSpecialtyRequest
import br.com.seutempo.api.adapters.web.model.response.specialty.SpecialtyResponse
import br.com.seutempo.api.core.ports.input.ManageSpecialtyInputPort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("specialty")
class RestSpecialtyController(
    private val manageSpecialtyUseCase: ManageSpecialtyInputPort,
) : SpecialtyOpenAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    override fun createSpecialty(
        @RequestBody newSpecialtyRequest: NewSpecialtyRequest,
    ) = manageSpecialtyUseCase.createNewSpecialty(newSpecialtyRequest)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun getAllSpecialty(): List<SpecialtyResponse> = manageSpecialtyUseCase.getAllSpecialty()

    @GetMapping("professional/{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun getSpecialtyByProfessional(
        @PathVariable id: Int,
    ): List<SpecialtyResponse> = manageSpecialtyUseCase.getSpecialtyByProfessional(id)

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun deleteSpecialtyById(
        @PathVariable id: Int,
    ) = manageSpecialtyUseCase.deleteSpecialtyById(id)

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun updateSpecialty(
        @PathVariable id: Int,
        @RequestBody updateSpecialtyRequest: UpdateSpecialtyRequest,
    ) = manageSpecialtyUseCase.updateSpecialty(id, updateSpecialtyRequest)

    @GetMapping("{ids}")
    @ResponseStatus(HttpStatus.OK)
    override fun getSpecialtyByIds(
        @PathVariable ids: List<Int>,
    ): List<SpecialtyResponse> = manageSpecialtyUseCase.findSpecialtyByIds(ids)
}
