package br.com.seutempo.api.repository.professional

import br.com.seutempo.api.model.professional.ProfessionalSpecialty
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfessionalSpecialtyRepository : JpaRepository<ProfessionalSpecialty, Int>
