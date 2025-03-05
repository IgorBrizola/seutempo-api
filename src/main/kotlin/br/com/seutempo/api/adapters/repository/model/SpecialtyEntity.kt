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

@Entity
@Table(name = "specialty")
data class SpecialtyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "name_specialty")
    val nameSpecialty: String,
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_category")
    val categoryEntity: CategoryEntity,
    @ManyToMany(mappedBy = "specialties")
    val professionalEntities: MutableList<ProfessionalEntity>? = mutableListOf(),
)
