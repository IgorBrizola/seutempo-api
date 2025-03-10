package br.com.seutempo.api.adapters.repository.jpa.professional

import br.com.seutempo.api.adapters.repository.model.ProfessionalEntity
import org.locationtech.jts.geom.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface ProfessionalJpaRepository :
    JpaRepository<ProfessionalEntity, Int>,
    JpaSpecificationExecutor<ProfessionalEntity> {
    fun existsByLinkNameProfessional(link: String): Boolean

    fun findProfessionalEntityBySpecialtiesIdAndUserActiveIsTrue(id: Int): MutableList<ProfessionalEntity>

    fun findProfessionalEntityBySpecialtiesCategoryEntityIdAndUserActiveIsTrue(id: Int): List<ProfessionalEntity>

    @Query("SELECT p FROM ProfessionalEntity p WHERE ST_Distance(p.location, :point) <= p.serviceRadiusKm * 1000 and p.user.active IS TRUE")
    fun findProfessionalsWithinRadius(
        @Param("point") point: Point,
    ): List<ProfessionalEntity>

    fun findProfessionalEntityByLinkNameProfessionalAndUserActiveIsTrue(linkName: String): Optional<ProfessionalEntity>

    fun findByIdAndUserActiveIsTrue(id: Int): Optional<ProfessionalEntity>

    fun findByIdAndUserActiveIsFalse(id: Int): Optional<ProfessionalEntity>

    fun existsByUserEmailAndUserActiveIsTrue(email: String): Boolean

    fun existsByUserCpfAndUserActiveIsTrue(cpf: String): Boolean

    fun existsByUserPhoneAndUserActiveIsTrue(phone: String): Boolean
}
