package br.com.seutempo.api.service.professional

import br.com.seutempo.api.repository.professional.ProfessionalSpecialtyRepository
import org.springframework.stereotype.Service

@Service
class ProfessionalSpecialtyService(
    private val professionalSpecialtyRepository: ProfessionalSpecialtyRepository,
)
