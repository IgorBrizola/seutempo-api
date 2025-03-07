package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.repository.model.ProfessionalEntity
import br.com.seutempo.api.core.domain.model.professional.Professional
import org.locationtech.jts.geom.Point
import java.math.BigDecimal

interface ManageProfessionalOutputPort {
    fun existsByLinkNameProfessional(link: String): Boolean

    fun findProfessionalEntityBySpecialtiesId(id: Int): MutableList<Professional>

    fun findProfessionalEntityBySpecialtiesCategoryEntityId(id: Int): List<Professional>

    fun findProfessionalsByFilters(
        name: String?,
        maxValue: BigDecimal?,
        minValue: BigDecimal?,
    ): List<Professional>

    fun findProfessionalsWithinRadius(point: Point): List<Professional>

    fun findProfessionalEntityByLinkNameProfessional(linkName: String): Professional

    fun findById(id: Int): Professional

    fun save(professional: Professional): ProfessionalEntity

    fun saveAll(professionals: MutableList<Professional>): List<ProfessionalEntity>

    fun disableProfessional(professional: Professional)

    fun activeProfessional(professional: Professional)
}
