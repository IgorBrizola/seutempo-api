package br.com.seutempo.api.repository.professional

import br.com.seutempo.api.model.professional.Professional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfessionalRepository : JpaRepository<Professional, Int>
