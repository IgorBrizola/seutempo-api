package br.com.seutempo.api.repository.professional

import br.com.seutempo.api.model.professional.Professional
import org.locationtech.jts.geom.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProfessionalRepository : JpaRepository<Professional, Int> {
    fun existsByLinkProfessional(link: String): Boolean

    fun findProfessionalBySpecialtiesId(id: Int): List<Professional>

    fun findProfessionalBySpecialtiesCategoryId(id: Int): List<Professional>

    @Query(
        "SELECT p FROM Professional p WHERE :name IS NULL OR :name = '' OR p.user.name LIKE %:name%",
    )
    fun findByUserNameOrProfessionals(
        @Param("name") name: String?,
    ): List<Professional>

    @Query("SELECT p FROM Professional p WHERE ST_Distance(p.location, :point) <= p.serviceRadiusKm * 1000")
    fun findProfessionalsWithinRadius(
        @Param("point") point: Point,
    ): List<Professional>
}
