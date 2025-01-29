package br.com.seutempo.api.service.specialty

import br.com.seutempo.api.mapper.specialty.SpecialtyMapper
import br.com.seutempo.api.model.exception.specialty.SpecialtyAlreadyExistsException
import br.com.seutempo.api.model.specialty.request.SpecialtyNewRequest
import br.com.seutempo.api.repository.specialty.SpecialtyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SpecialtyService(
    private val specialtyRepository: SpecialtyRepository,
    private val specialtyMapper: SpecialtyMapper,
) {
    @Transactional
    fun createNewSpecialty(specialtyNewRequest: SpecialtyNewRequest) {
        if (specialtyRepository.existsByNameSpecialty(specialtyNewRequest.name)) {
            throw SpecialtyAlreadyExistsException("Specialty (${specialtyNewRequest.name}) already exist")
        }
        specialtyRepository.save(specialtyMapper.specialtyNewRequestToSpecialty(specialtyNewRequest))
    }
}
