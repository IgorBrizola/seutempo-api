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
import org.locationtech.jts.geom.Point
import java.math.BigDecimal

@Entity
@Table(name = "professional")
data class ProfessionalEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @OneToOne(cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "id_users")
    val user: UsersEntity,
    @Column(name = "url_professional")
    val urlProfessional: String,
    @Column(name = "link_professional")
    val linkNameProfessional: String,
    @Column(name = "value_hour")
    val valueHour: BigDecimal,
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "professional_specialty",
        joinColumns = [JoinColumn(name = "id_professional")],
        inverseJoinColumns = [JoinColumn(name = "id_specialty")],
    )
    val specialties: MutableList<SpecialtyEntity> = mutableListOf(),
    @Column(name = "cep")
    val cep: String,
    @Column(name = "latitude")
    val lat: Double,
    @Column(name = "longitude")
    val lon: Double,
    @Column(name = "service_radius_km")
    val serviceRadiusKm: Int,
    @Column(name = "location", columnDefinition = "geography")
    val location: Point,
)
