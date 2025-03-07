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
import org.hibernate.proxy.HibernateProxy
import org.locationtech.jts.geom.Point

@Entity
@Table(name = "client")
open class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @OneToOne(cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "id_users", nullable = false)
    open var user: UsersEntity? = null

    @Column(name = "surname", nullable = false)
    open var surname: String? = null

    @Column(name = "cep", nullable = false)
    open var cep: String? = null

    @Column(name = "state", nullable = false)
    open var state: String? = null

    @Column(name = "city", nullable = false)
    open var city: String? = null

    @Column(name = "neighborhood", nullable = false)
    open var neighborhood: String? = null

    @Column(name = "street", nullable = false)
    open var street: String? = null

    @Column(name = "number", nullable = false)
    open var number: Int? = null

    @Column(name = "complement", nullable = false)
    open var complement: String? = null

    @Column(name = "additional_address", nullable = false)
    open var additionalAddress: String? = null

    @Column(name = "type_address", nullable = false)
    @Enumerated(EnumType.STRING)
    open var typeAddress: TypeAddress? = null

    @Column(name = "latitude", nullable = false)
    open var latitude: Double? = null

    @Column(name = "longitude", nullable = false)
    open var longitude: Double? = null

    @Column(name = "location", nullable = false)
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
        other as ClientEntity

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()
}

enum class TypeAddress { RESIDENTIAL, COMMERCIAL }
