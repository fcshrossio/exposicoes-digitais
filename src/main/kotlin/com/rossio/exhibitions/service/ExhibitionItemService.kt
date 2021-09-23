package com.rossio.exhibitions.service

import com.rossio.exhibitions.dto.SubAboutItemDTO
import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.exception.WrongTypeException
import com.rossio.exhibitions.model.*

import com.rossio.exhibitions.model.ExhibitionItemsRepository
import org.springframework.stereotype.Service

@Service
class ExhibitionItemService(
    val exhibitionItemsRepository : ExhibitionItemsRepository,
    val markersRepository: MarkersRepository,
    val subAboutRepository: SubAboutRepository,
    val subTextRepository: SubTextRepository
) {
    fun getAllExhibitionItems(): List<ExhibitionItemDAO> =
        exhibitionItemsRepository.findAll()

    fun getOneExhibitionItem(id : Long) : ExhibitionItemDAO =
        exhibitionItemsRepository.findById(id).orElseThrow { NotFoundException("No Exhibition Item with id: $id found") }

    fun createOneExhibitionItem(item : ExhibitionItemDAO) : ExhibitionItemDAO =
        exhibitionItemsRepository.save(item)

    fun editOneExhibitionItem(item : ExhibitionItemDAO, newItem : ExhibitionItemDAO): ExhibitionItemDAO {
        item.editItem(newItem)
        exhibitionItemsRepository.save(item)
        return item
    }

    fun deleteOneExhibitionItem(id: Long)  =
        exhibitionItemsRepository.delete(getOneExhibitionItem(id))

    fun createMarker(markerDAO: MarkerDAO) : MarkerDAO =
        markersRepository.save(markerDAO)

    fun createSubAbout(subItem: SubAboutDAO) : SubAboutDAO =
        subAboutRepository.save(subItem)

    fun createSubText(subItem: SubTextDAO) : SubTextDAO =
        subTextRepository.save(subItem)

    fun addMarker(itemId: Long, markerDAO: MarkerDAO) {
     var item = getOneExhibitionItem(itemId)

            exhibitionItemsRepository.save(item)


    }

    fun removeMarker(markerDAO: MarkerDAO) {


    }

    fun addSubAbout(itemId: Long, subAboutDAO: SubAboutDAO) {


    }

    fun removeSubAbout(subAboutDAO: SubAboutDAO) {


    }

    fun addSubText(itemId: Long, subTextDAO: SubTextDAO) {


    }

    fun removeSubText( subTextDAO: SubTextDAO) {

    }
}