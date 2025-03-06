package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.professional.ProfessionalJpaRepository
import br.com.seutempo.api.adapters.repository.model.ProfessionalEntity
import br.com.seutempo.api.adapters.web.mapper.professional.ProfessionalMapper
import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse
import br.com.seutempo.api.core.domain.exceptions.ResourceNotFoundException
import br.com.seutempo.api.core.domain.model.professional.Professional
import br.com.seutempo.api.core.ports.output.ManageProfessionalOutputPort
import org.locationtech.jts.geom.Point
import java.math.BigDecimal

class ManageProfessionalRepository(
    private val professionalJpaRepository: ProfessionalJpaRepository,
    private val professionalMapper: ProfessionalMapper,
) : ManageProfessionalOutputPort {
    override fun existsByLinkNameProfessional(link: String): Boolean = professionalJpaRepository.existsByLinkNameProfessional(link)

    override fun findProfessionalEntityBySpecialtiesId(id: Int): MutableList<Professional> =
        professionalMapper.toListDomain(professionalJpaRepository.findProfessionalEntityBySpecialtiesId(id))

    override fun findProfessionalEntityBySpecialtiesCategoryEntityId(id: Int): List<ProfessionalResponse> =
        professionalMapper.toListProfessionalResponse(
            professionalMapper.toListDomain(professionalJpaRepository.findProfessionalEntityBySpecialtiesCategoryEntityId(id)),
        )

    override fun findProfessionalsByFilters(
        name: String?,
        value: BigDecimal?,
    ): List<ProfessionalResponse> =
        professionalMapper.toListProfessionalResponse(
            professionalMapper.toListDomain(professionalJpaRepository.findProfessionalsByFilters(name, value)),
        )

    override fun findProfessionalsWithinRadius(point: Point): List<ProfessionalResponse> =
        professionalMapper.toListProfessionalResponse(
            professionalMapper.toListDomain(professionalJpaRepository.findProfessionalsWithinRadius(point)),
        )

    override fun findProfessionalEntityByLinkNameProfessional(linkName: String): ProfessionalResponse =
        professionalMapper.toResponse(
            professionalMapper.toDomain(
                professionalJpaRepository.findProfessionalEntityByLinkNameProfessional(linkName).orElseThrow {
                    throw ResourceNotFoundException("professional linkName not found! - $linkName")
                },
            ),
        )

    override fun findById(id: Int): Professional =
        professionalMapper.toDomain(
            professionalJpaRepository.findById(id).orElseThrow {
                throw ResourceNotFoundException("professional not found! - $id")
            },
        )

    override fun save(professional: Professional): ProfessionalEntity =
        professionalJpaRepository.save(professionalMapper.toEntity(professional))

    override fun saveAll(professionals: MutableList<Professional>): List<ProfessionalEntity> =
        professionalJpaRepository.saveAll(professionalMapper.toListEntity(professionals))
}
