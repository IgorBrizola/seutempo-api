package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.repository.model.ProfessionalEntity
import org.locationtech.jts.geom.Point
import java.math.BigDecimal
import java.util.Optional

interface ManageProfessionalOutputPort {
    fun existsByLinkNameProfessional(link: String): Boolean

    fun findProfessionalEntityBySpecialtiesId(id: Int): List<ProfessionalEntity>

    fun findProfessionalEntityBySpecialtiesCategoryEntityId(id: Int): List<ProfessionalEntity>

    fun findProfessionalsByFilters(
        name: String?,
        value: BigDecimal?,
    ): List<ProfessionalEntity>

    fun findProfessionalsWithinRadius(point: Point): List<ProfessionalEntity>

    fun findProfessionalEntityByLinkNameProfessional(linkName: String): Optional<ProfessionalEntity>
}
