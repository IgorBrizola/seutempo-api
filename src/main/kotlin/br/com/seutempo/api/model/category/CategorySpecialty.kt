package br.com.seutempo.api.model.category

import br.com.seutempo.api.model.specialty.Specialty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "category_specialty")
data class CategorySpecialty(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @OneToMany
    @JoinColumn(name = "id_category")
    val idCategory: List<Category>,
    @OneToMany
    @JoinColumn(name = "id_specialty")
    val idSpecialty: List<Specialty>,
)
