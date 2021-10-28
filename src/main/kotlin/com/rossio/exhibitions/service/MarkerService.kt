package com.rossio.exhibitions.service

import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.MarkerDAO
import com.rossio.exhibitions.model.MarkersRepository
import org.springframework.stereotype.Service

@Service
class MarkerService(
    val markersRepository: MarkersRepository
) {
    fun getAllMarkers(): List<MarkerDAO> =
        markersRepository.findAll()

    fun getOneMarker(id:Long): MarkerDAO =
        markersRepository.findById(id).orElseThrow { NotFoundException("No Item with id: $id found") }

    fun createOneMarker(marker : MarkerDAO) : MarkerDAO {
        if(marker.id == 0L){
            return markersRepository.save(marker)
        }
        throw Exception("id is not 0")
    }

    fun editOneMarker(marker: MarkerDAO, newMarker : MarkerDAO) : MarkerDAO {
        if (marker.editMarker(newMarker)) {
            return markersRepository.save(marker)
        } else {
            return marker
        }
    }

    fun deleteOneMarker(id : Long) =
        markersRepository.delete(getOneMarker(id))
}