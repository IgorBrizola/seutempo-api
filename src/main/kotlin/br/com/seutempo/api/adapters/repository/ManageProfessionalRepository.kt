package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.professional.ProfessionalJpaRepository
import br.com.seutempo.api.adapters.repository.model.ProfessionalEntity
import br.com.seutempo.api.core.ports.output.ManageProfessionalOutputPort
import org.locationtech.jts.geom.Point
import java.math.BigDecimal
import java.util.Optional

class ManageProfessionalRepository(
    private val professionalJpaRepository: ProfessionalJpaRepository,
) : ManageProfessionalOutputPort {
    // TODO: configure manage repository professional
    override fun existsByLinkNameProfessional(link: String): Boolean = professionalJpaRepository.existsByLinkNameProfessional(link)

    override fun findProfessionalEntityBySpecialtiesId(id: Int): List<ProfessionalEntity> =
        professionalJpaRepository.findProfessionalEntityBySpecialtiesId(id)

    override fun findProfessionalEntityBySpecialtiesCategoryEntityId(id: Int): List<ProfessionalEntity> =
        professionalJpaRepository.findProfessionalEntityBySpecialtiesCategoryEntityId(id)

    override fun findProfessionalsByFilters(
        name: String?,
        value: BigDecimal?,
    ): List<ProfessionalEntity> = professionalJpaRepository.findProfessionalsByFilters(name, value)

    override fun findProfessionalsWithinRadius(point: Point): List<ProfessionalEntity> =
        professionalJpaRepository.findProfessionalsWithinRadius(point)

    override fun findProfessionalEntityByLinkNameProfessional(linkName: String): Optional<ProfessionalEntity> =
        professionalJpaRepository.findProfessionalEntityByLinkNameProfessional(linkName)
}
