package br.com.seutempo.api.model.client

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "client")
data class Client(
    @OneToOne
    @JoinColumn(name = "id")
    val id: Int?,
    val surname: String?,
)
