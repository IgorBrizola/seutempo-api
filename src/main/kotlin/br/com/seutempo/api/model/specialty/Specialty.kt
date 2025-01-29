package br.com.seutempo.api.model.specialty

import br.com.seutempo.api.model.category.Category
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "specialty")
data class Specialty(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "name_specialty")
    val nameSpecialty: String,
    @ManyToOne(cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "id_category")
    val category: Category,
)
