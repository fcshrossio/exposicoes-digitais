package com.rossio.exhibitions.service

import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.DigitalResourceDAO
import com.rossio.exhibitions.model.DigitalResourceRepository
import org.springframework.stereotype.Service

@Service
class DigitalResourceService(
    val digitalResourceRepository: DigitalResourceRepository
) {

    fun getAllDigitalResources() : List<DigitalResourceDAO> =
        digitalResourceRepository.findAll()

    fun addOneDigitalResource(resource : DigitalResourceDAO) : DigitalResourceDAO =
        digitalResourceRepository.save(resource)

    fun getOneDigitalResource(id: Long) : DigitalResourceDAO =
        digitalResourceRepository.findById(id).orElseThrow{ NotFoundException("No Digital Resource with id: $id found")}

    fun removeDigitalResource(id:Long) =
        digitalResourceRepository.delete(getOneDigitalResource(id))
}