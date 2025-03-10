package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.professional.ProfessionalJpaRepository
import br.com.seutempo.api.adapters.repository.model.ProfessionalEntity
import br.com.seutempo.api.adapters.web.mapper.professional.ProfessionalMapper
import br.com.seutempo.api.core.domain.exceptions.ResourceNotFoundException
import br.com.seutempo.api.core.domain.model.professional.Professional
import br.com.seutempo.api.core.domain.model.professional.request.UpdateProfessionalInput
import br.com.seutempo.api.core.ports.output.ManageProfessionalOutputPort
import org.apache.logging.log4j.LogManager
import org.locationtech.jts.geom.Point
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
class ManageProfessionalRepository(
    private val professionalJpaRepository: ProfessionalJpaRepository,
    private val professionalMapper: ProfessionalMapper,
) : ManageProfessionalOutputPort {
    private val log = LogManager.getLogger(javaClass)

    override fun existsByLinkNameProfessional(link: String): Boolean = professionalJpaRepository.existsByLinkNameProfessional(link)

    override fun findProfessionalEntityBySpecialtiesId(id: Int): MutableList<Professional> =
        professionalMapper.toListDomain(
            professionalJpaRepository.findProfessionalEntityBySpecialtiesIdAndUserActiveIsTrue(id),
        )

    override fun findProfessionalEntityBySpecialtiesCategoryEntityId(id: Int): List<Professional> =
        professionalMapper.toListDomain(
            professionalJpaRepository.findProfessionalEntityBySpecialtiesCategoryEntityIdAndUserActiveIsTrue(id),
        )

    override fun findProfessionalsByFilters(
        name: String?,
        maxValue: BigDecimal?,
        minValue: BigDecimal?,
    ): List<Professional> {
        val spec = buildSpecFilter(name, maxValue, minValue)
        return professionalMapper.toListDomain(
            professionalJpaRepository.findAll(spec).filter { it.user?.active == true },
        )
    }

    private fun buildSpecFilter(
        name: String?,
        maxValue: BigDecimal?,
        minValue: BigDecimal?,
    ): Specification<ProfessionalEntity> {
        var spec: Specification<ProfessionalEntity> = Specification.where(null)

        name?.let {
            spec = spec.and(ProfessionalJpaSpecs.containsNameProfessional(name))
        }
        if (minValue != null && maxValue != null) {
            spec = spec.and(ProfessionalJpaSpecs.containsMinValueAndMaxProfessional(minValue, maxValue))
        } else {
            minValue?.let {
                spec = spec.and(ProfessionalJpaSpecs.containsMinValueProfessional(minValue))
            }
            maxValue?.let {
                spec = spec.and(ProfessionalJpaSpecs.containsMaxValueProfessional(maxValue))
            }
        }
        return spec
    }

    override fun findProfessionalsWithinRadius(point: Point): List<Professional> =
        professionalMapper.toListDomain(professionalJpaRepository.findProfessionalsWithinRadius(point))

    // TODO: ADD SPECIFICATIONS
    override fun findProfessionalEntityByLinkNameProfessional(linkName: String): Professional =
        professionalMapper.toDomain(
            professionalJpaRepository.findProfessionalEntityByLinkNameProfessionalAndUserActiveIsTrue(linkName).orElseThrow {
                throw ResourceNotFoundException("professional linkName not found! - $linkName")
            },
        )

    override fun findById(id: Int): Professional =
        professionalMapper.toDomain(
            professionalJpaRepository.findByIdAndUserActiveIsTrue(id).orElseThrow {
                throw ResourceNotFoundException("professional not found! - $id")
            },
        )

    override fun findByIdActive(id: Int): Professional =
        professionalMapper.toDomain(
            professionalJpaRepository.findByIdAndUserActiveIsFalse(id).orElseThrow {
                throw ResourceNotFoundException("professional not found! - $id")
            },
        )

    override fun save(professional: Professional): Professional =
        professionalMapper.toDomain(professionalJpaRepository.save(professionalMapper.toEntity(professional)))

    override fun disableProfessional(professional: Professional) {
        val professionalEntity = professionalMapper.toEntity(professional)
        professionalEntity.user?.active = false

        log.info("Disable professional - ${professional.id} and user - ${professional.user.id}")
        professionalJpaRepository.save(professionalEntity)
    }

    override fun activeProfessional(professional: Professional) {
        val professionalEntity = professionalMapper.toEntity(professional)
        professionalEntity.user?.active = true

        log.info("Active professional - ${professional.id} and user - ${professional.user.id}")
        professionalJpaRepository.save(professionalEntity)
    }

    override fun updateProfessional(
        professional: Professional,
        professionalInput: UpdateProfessionalInput,
    ): Professional {
        val professionalEntity = professionalMapper.toEntity(professional)

        professionalInput.valueHour?.let {
            professionalEntity.valueHour = it
        }

        professionalInput.photoUser?.let {
            professionalEntity.user?.photoUser = it
        }

        log.info("Update professional - ${professional.id} and user - ${professional.user.id}")
        professionalJpaRepository.save(professionalEntity)

        return professionalMapper.toDomain(professionalEntity)
    }

    override fun existsByUserEmail(email: String): Boolean = professionalJpaRepository.existsByUserEmailAndUserActiveIsTrue(email)

    override fun existsByUserCpf(cpf: String): Boolean = professionalJpaRepository.existsByUserCpfAndUserActiveIsTrue(cpf)

    override fun existsByUserPhone(phone: String): Boolean = professionalJpaRepository.existsByUserPhoneAndUserActiveIsTrue(phone)

    override fun saveAll(professionals: MutableList<Professional>): List<ProfessionalEntity> =
        professionalJpaRepository.saveAll(professionalMapper.toListEntity(professionals))
}
