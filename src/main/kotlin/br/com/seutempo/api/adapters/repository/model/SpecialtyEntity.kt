package br.com.seutempo.api.adapters.repository.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.proxy.HibernateProxy

@Entity
@Table(name = "specialty")
open class SpecialtyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Column(name = "name_specialty", nullable = false)
    open var nameSpecialty: String? = null

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_category", nullable = false)
    open var categoryEntity: CategoryEntity? = null

    @ManyToMany(mappedBy = "specialties")
    open var professionalEntities: MutableList<ProfessionalEntity>? = mutableListOf()

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
        other as SpecialtyEntity

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()
}
