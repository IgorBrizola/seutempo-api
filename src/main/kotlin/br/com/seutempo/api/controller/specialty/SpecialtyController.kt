package br.com.seutempo.api.controller.specialty

import br.com.seutempo.api.service.specialty.SpecialtyService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("specialty")
class SpecialtyController(
    private val specialtyService: SpecialtyService,
)
