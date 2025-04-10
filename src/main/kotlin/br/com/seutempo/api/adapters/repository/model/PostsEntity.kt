package br.com.seutempo.api.adapters.repository.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.proxy.HibernateProxy

@Entity
@Table(name = "posts")
open class PostsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Column(name = "title", nullable = false)
    open var title: String? = null

    @Column(name = "img_url", nullable = false)
    open var imgUrl: String? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professional", nullable = false)
    open var professional: ProfessionalEntity? = null

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
        other as PostsEntity

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()
}
