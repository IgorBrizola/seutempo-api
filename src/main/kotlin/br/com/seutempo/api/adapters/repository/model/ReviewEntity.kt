package br.com.seutempo.api.adapters.repository.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "reviews")
open class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int? = null

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    open var client: ClientEntity? = null

    @ManyToOne
    @JoinColumn(name = "id_professional", nullable = false)
    open var idProfessional: ProfessionalEntity? = null

    @Column(name = "rating", nullable = false)
    open var rating: Double? = null

    @Column(name = "comment", nullable = true)
    open var comment: String? = null

    @Column(name = "created_at", nullable = true)
    open var createdAt: LocalDateTime? = null
}
