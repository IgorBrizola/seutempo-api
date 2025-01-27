package br.com.seutempo.api.service.client

import br.com.seutempo.api.repository.client.ClientRepository
import org.springframework.stereotype.Service

@Service
class ClientService(
    private val clientRepository: ClientRepository,
)
