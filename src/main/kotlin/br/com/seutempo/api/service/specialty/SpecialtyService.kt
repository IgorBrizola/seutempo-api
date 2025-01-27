package br.com.seutempo.api.service.specialty

import br.com.seutempo.api.repository.category.CategorySpecialtyRepository
import org.springframework.stereotype.Service

@Service
class SpecialtyService(
    private val specialtyRepository: CategorySpecialtyRepository,
)
