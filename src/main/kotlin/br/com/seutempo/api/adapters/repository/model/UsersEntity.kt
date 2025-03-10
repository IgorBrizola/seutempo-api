package br.com.seutempo.api.adapters.repository.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.proxy.HibernateProxy
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "users")
open class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Column(name = "name", nullable = false)
    open var name: String? = null

    @Column(name = "middle_name", nullable = false)
    open var middleName: String? = null

    @Column(name = "last_name", nullable = false)
    open var lastName: String? = null

    @Column(name = "email", nullable = false)
    open var email: String? = null

    @Column(name = "password", nullable = false)
    open var password: String? = null

    @Column(name = "cpf", nullable = false)
    open var cpf: String? = null

    @Column(name = "phone", nullable = false)
    open var phone: String? = null

    @Column(name = "photo_user", nullable = false)
    open var photoUser: String? = null

    @Column(name = "date_anniversary", nullable = false)
    open var dateAnniversary: LocalDate? = null

    @Column(name = "created_at", nullable = false)
    open var createdAt: LocalDateTime? = LocalDateTime.now()

    @Column(name = "type_user", nullable = false)
    @Enumerated(value = EnumType.STRING)
    open var typeUser: TypeUser? = null

    @Column(name = "active", nullable = false)
    open var active: Boolean? = true

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
        other as UsersEntity

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()
}

enum class TypeUser { CLIENT, PROFESSIONAL }
