package br.com.seutempo.api.repository.client

import br.com.seutempo.api.model.client.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Int>
