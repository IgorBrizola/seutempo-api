package br.com.seutempo.api.controller.professional

import br.com.seutempo.api.service.professional.ProfessionalService
import br.com.seutempo.api.service.professional.ProfessionalSpecialtyService
import br.com.seutempo.api.service.users.UsersService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("professional")
class ProfessionalController(
    private val professionalService: ProfessionalService,
    private val professionalSpecialty: ProfessionalSpecialtyService,
    private val usersService: UsersService,
)
