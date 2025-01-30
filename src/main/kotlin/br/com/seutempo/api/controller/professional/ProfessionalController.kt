package br.com.seutempo.api.controller.professional

import br.com.seutempo.api.model.professional.request.UsersProfessionalRequestNew
import br.com.seutempo.api.service.professional.ProfessionalService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("professional")
class ProfessionalController(
    private val professionalService: ProfessionalService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUsersProfessional(
        @RequestBody professionalRequestNew: UsersProfessionalRequestNew,
    ) = professionalService.createUsersProfessional(professionalRequestNew)
}
