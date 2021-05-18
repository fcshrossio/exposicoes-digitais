package com.rossio.exhibitions.service

import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExhibitionSubItemService(
    val subTextRepository: SubTextRepository,
    val subAboutRepository: SubAboutRepository,
    val markersRepository: MarkersRepository
) {

    fun getAllMarkers(): List<MarkerDAO> =
        markersRepository.findAll()

    fun getOneMarker(id:Long): MarkerDAO =
        markersRepository.findById(id).orElseThrow { NotFoundException("No Item with id: $id found") }

    fun getAllSubAboutItems(): List<SubAboutDAO> =
        subAboutRepository.findAll()

    fun getOneSubAboutItem(id:Long) : SubAboutDAO =
        subAboutRepository.findById(id).orElseThrow { NotFoundException("No Item with id: $id found") }

    fun getAllSubTextItems(): List<SubTextDAO> =
        subTextRepository.findAll()

    fun getOneSubTextItem(id:Long) : SubTextDAO =
        subTextRepository.findById(id).orElseThrow { NotFoundException("No Item with id: $id found") }


    fun deleteSubTextItem(id: Long) =
        subTextRepository.delete(getOneSubTextItem(id))

    fun deleteSubAboutItem(id: Long) =
        subAboutRepository.delete(getOneSubAboutItem(id))
}