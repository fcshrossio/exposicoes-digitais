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
        if (item is MapItemDAO){
            item.addMarker(createMarker(markerDAO))
            exhibitionItemsRepository.save(item)
        }
        else
        {

        }

    }

    fun removeMarker(markerDAO: MarkerDAO) {
        var mapItem: ExhibitionItemDAO = getOneExhibitionItem(markerDAO.mapItem.id)
        if ( mapItem is MapItemDAO)
        {
            mapItem.removeMarker(markerDAO)
            exhibitionItemsRepository.save(mapItem)
        }
        else
        {
            WrongTypeException("")
        }
    }

    fun addSubAbout(itemId: Long, subAboutDAO: SubAboutDAO) {
        var item = getOneExhibitionItem(itemId)
        if (item is AboutItemDAO){
           item.addSubAbout(createSubAbout(subAboutDAO))
            exhibitionItemsRepository.save(item)
        }
        else
        {

        }

    }

    fun removeSubAbout(subAboutDAO: SubAboutDAO) {
        var item: ExhibitionItemDAO = getOneExhibitionItem(subAboutDAO.aboutItemDAO.id)
        if (item is AboutItemDAO){
            item.removeSubAbout(subAboutDAO)
            exhibitionItemsRepository.save(item)
        }
        else
        {
            WrongTypeException("")
        }

    }

    fun addSubText(itemId: Long, subTextDAO: SubTextDAO) {
        var item = getOneExhibitionItem(itemId)
        if (item is TextItemDAO){
            item.addSubText(createSubText(subTextDAO))
            exhibitionItemsRepository.save(item)
        }
        else
        {
             NotFoundException("Item is not Text item")
        }

    }

    fun removeSubText( subTextDAO: SubTextDAO) {
        var item: ExhibitionItemDAO = getOneExhibitionItem(subTextDAO.textItemDAO.id)
        if (item is TextItemDAO){
            item.removeSubText(subTextDAO)
            exhibitionItemsRepository.save(item)
        }
        else
        {
            WrongTypeException("")
        }

    }
}