package com.rossio.exhibitions.service

import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.EditorDAO
import com.rossio.exhibitions.model.EditorRepository
import org.springframework.stereotype.Service

@Service
class EditorService(
    val editorRepository: EditorRepository
) {

    fun getAllEditors() : List<EditorDAO> =
        editorRepository.findAll()

    fun getOneEditor(id: Long): EditorDAO =
        editorRepository.findById(id).orElseThrow { NotFoundException("No Editor with ID: $id found")}

    fun addOneEditor(editorDAO: EditorDAO) =
        editorRepository.save(editorDAO)

    fun deleteEditor(id:Long) =
        editorRepository.delete(getOneEditor(id))
}