package br.com.seutempo.api.adapters.repository.jpa.professional

import br.com.seutempo.api.adapters.repository.model.ProfessionalEntity
import org.locationtech.jts.geom.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.Optional

@Repository
interface ProfessionalJpaRepository : JpaRepository<ProfessionalEntity, Int> {
    // TODO: add jpa specifications

    fun existsByLinkNameProfessional(link: String): Boolean

    fun findProfessionalEntityBySpecialtiesId(id: Int): MutableList<ProfessionalEntity>

    fun findProfessionalEntityBySpecialtiesCategoryEntityId(id: Int): List<ProfessionalEntity>

    @Query(
        "SELECT p FROM ProfessionalEntity p " +
            "WHERE (:name IS NULL OR :name = '' OR p.user.name LIKE %:name%) " +
            "AND (:value IS NULL OR p.valueHour <= :value)",
    )
    fun findProfessionalsByFilters(
        @Param("name") name: String?,
        @Param("value") value: BigDecimal?,
    ): List<ProfessionalEntity>

    @Query("SELECT p FROM ProfessionalEntity p WHERE ST_Distance(p.location, :point) <= p.serviceRadiusKm * 1000")
    fun findProfessionalsWithinRadius(
        @Param("point") point: Point,
    ): List<ProfessionalEntity>

    fun findProfessionalEntityByLinkNameProfessional(linkName: String): Optional<ProfessionalEntity>
}
