package br.com.seutempo.api.adapters.repository.jpa.client

import br.com.seutempo.api.adapters.repository.model.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ClientJpaRepository : JpaRepository<ClientEntity, Int>
