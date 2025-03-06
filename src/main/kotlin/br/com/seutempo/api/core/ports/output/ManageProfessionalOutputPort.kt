package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.repository.model.ProfessionalEntity
import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse
import br.com.seutempo.api.core.domain.model.professional.Professional
import org.locationtech.jts.geom.Point
import java.math.BigDecimal

interface ManageProfessionalOutputPort {
    fun existsByLinkNameProfessional(link: String): Boolean

    fun findProfessionalEntityBySpecialtiesId(id: Int): MutableList<Professional>

    fun findProfessionalEntityBySpecialtiesCategoryEntityId(id: Int): List<ProfessionalResponse>

    fun findProfessionalsByFilters(
        name: String?,
        value: BigDecimal?,
    ): List<ProfessionalResponse>

    fun findProfessionalsWithinRadius(point: Point): List<ProfessionalResponse>

    fun findProfessionalEntityByLinkNameProfessional(linkName: String): ProfessionalResponse

    fun findById(id: Int): Professional

    fun save(professional: Professional): ProfessionalEntity

    fun saveAll(professionals: MutableList<Professional>): List<ProfessionalEntity>
}
