package br.com.seutempo.api.service.address

import br.com.seutempo.api.repository.address.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressService(
    private val addressRepository: AddressRepository,
)
