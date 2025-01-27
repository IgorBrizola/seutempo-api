package br.com.seutempo.api.model.professional

import br.com.seutempo.api.model.specialty.Specialty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "professional_specialty")
data class ProfessionalSpecialty(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @OneToMany
    @JoinColumn(name = "id_professional")
    val idProfessional: List<Professional>,
    @OneToMany
    @JoinColumn(name = "id_specialty")
    val idSpecialty: List<Specialty>,
)
