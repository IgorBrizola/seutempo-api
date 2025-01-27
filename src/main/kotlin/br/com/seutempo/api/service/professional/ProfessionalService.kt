package br.com.seutempo.api.service.professional

import br.com.seutempo.api.repository.professional.ProfessionalRepository
import org.springframework.stereotype.Service

@Service
class ProfessionalService(
    private val professionalRepository: ProfessionalRepository,
)
