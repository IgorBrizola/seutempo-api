package br.com.seutempo.api.repository.address

import br.com.seutempo.api.model.address.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<Address, Int>
