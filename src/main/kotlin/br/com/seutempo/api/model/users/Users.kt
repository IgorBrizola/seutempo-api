package br.com.seutempo.api.model.users

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "name")
    val name: String,
    @Column(name = "last_name")
    val lastName: String,
    @Column(name = "email")
    val email: String,
    @Column(name = "password")
    val password: String,
    @Column(name = "cpf")
    val cpf: String,
    @Column(name = "phone")
    val phone: String,
    @Column(name = "photoUser")
    val photoUser: String,
    @Column(name = "date_anniversary")
    val dateAnniversary: LocalDate,
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "type_user")
    @Enumerated(value = EnumType.STRING)
    val typeUser: TypeUser,
    @Column(name = "active")
    val active: Boolean = true,
)

enum class TypeUser { CLIENT, PROFESSIONAL }
