package br.com.seutempo.api.adapters.repository.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.locationtech.jts.geom.Point

@Entity
@Table(name = "client")
data class ClientEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @OneToOne(cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "id_users")
    val user: Users,
    @Column(name = "surname")
    val surname: String? = null,
    @Column(name = "cep")
    val cep: String?,
    @Column(name = "state")
    val state: String?,
    @Column(name = "city")
    val city: String?,
    @Column(name = "neighborhood")
    val neighborhood: String?,
    @Column(name = "street")
    val street: String?,
    @Column(name = "number")
    val number: Int?,
    @Column(name = "complement")
    val complement: String?,
    @Column(name = "additional_address")
    val additionalAddress: String?,
    @Column(name = "type_address")
    @Enumerated(EnumType.STRING)
    val typeAddress: TypeAddress?,
    @Column(name = "latitude")
    val latitude: Double?,
    @Column(name = "longitude")
    val longitude: Double?,
    @Column(name = "location")
    val location: Point?,
)

enum class TypeAddress { RESIDENTIAL, COMMERCIAL }
