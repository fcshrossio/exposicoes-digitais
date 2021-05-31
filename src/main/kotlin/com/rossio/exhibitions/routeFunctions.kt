package com.rossio.exhibitions

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*

fun mapItemDTOtoDAO(item: ExhibitionItemDTO, exhibition: ExhibitionDAO) : ExhibitionItemDAO =

    when (item) {
        is IntroductionItemDTO ->  IntroductionItemDAO(item,exhibition)
        is TextItemDTO ->  TextItemDAO(item,exhibition)
        is MapItemDTO ->  MapItemDAO(item,exhibition)
        is AboutItemDTO ->  AboutItemDAO(item,exhibition)
        else -> throw NotFoundException("") //TODO exception
    }

fun mapItemDAOtoDTO(item: ExhibitionItemDAO) : ExhibitionItemDTO =

    when (item) {
        is IntroductionItemDAO -> IntroductionItemDTO(item)
        is TextItemDAO -> TextItemDTO(item)
        is MapItemDAO ->  MapItemDTO(item)
        is AboutItemDAO -> AboutItemDTO(item)
        else -> throw NotFoundException("") //TODO exception
    }