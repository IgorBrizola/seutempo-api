package br.com.seutempo.api.adapters.repository.jpa.client

import br.com.seutempo.api.adapters.repository.model.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientJpaRepository : JpaRepository<ClientEntity, Int>
