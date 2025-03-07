package br.com.seutempo.api.adapters.repository.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.proxy.HibernateProxy
import org.locationtech.jts.geom.Point
import java.math.BigDecimal

@Entity
@Table(name = "professional")
open class ProfessionalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @OneToOne(cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "id_users", nullable = false)
    open var user: UsersEntity? = null

    @Column(name = "url_professional", nullable = false)
    open var urlProfessional: String? = null

    @Column(name = "link_professional", nullable = false)
    open var linkNameProfessional: String? = null

    @Column(name = "value_hour", nullable = false)
    open var valueHour: BigDecimal? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "professional_specialty",
        joinColumns = [JoinColumn(name = "id_professional")],
        inverseJoinColumns = [JoinColumn(name = "id_specialty")],
    )
    open var specialties: MutableList<SpecialtyEntity>? = mutableListOf()

    @Column(name = "cep", nullable = false)
    open var cep: String? = null

    @Column(name = "latitude", nullable = false)
    open var lat: Double? = null

    @Column(name = "longitude", nullable = false)
    open var lon: Double? = null

    @Column(name = "service_radius_km", nullable = false)
    open var serviceRadiusKm: Int? = null

    @Column(name = "location", columnDefinition = "geography", nullable = false)
    open var location: Point? = null

    fun isNew() = id == null

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (other.javaClass != this.javaClass) return false

        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as ProfessionalEntity

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()
}
