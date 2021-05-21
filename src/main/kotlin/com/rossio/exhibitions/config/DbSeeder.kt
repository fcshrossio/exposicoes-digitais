package com.rossio.exhibitions.config

import com.rossio.exhibitions.controller.ExhibitionController
import com.rossio.exhibitions.dto.DigitalResourceDTO
import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.dto.UserDTO
import com.rossio.exhibitions.model.*
import com.rossio.exhibitions.service.*
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
class DbSeeder (
    val exhibitionService: ExhibitionService,
    val exhibitionItemService: ExhibitionItemService,
    val editorService: EditorService,
    val collaboratorService: CollaboratorService,
    val digitalResourceService: DigitalResourceService,
    val exhibitionController: ExhibitionController
) : CommandLineRunner {
    val runSeeder = false

    override fun run(vararg args: String?) {
        if (!runSeeder) {
            print("Database seeder disabled.")
            return
        }




        var editorDTO = UserDTO(0,"Henrique Raposo")

        var editorDAO = EditorDAO(0,"Henrique Raposo")

        editorService.addOneEditor(editorDAO)

        var collaboratorDTO = UserDTO(0,"Colaborador Raposo")

        var collaboratorDAO = CollaboratorDAO(collaboratorDTO)

        collaboratorService.addOneCollaborator(collaboratorDAO)

        var digital = DigitalResourceDTO(0,"NOME")

        val digitalDAO = DigitalResourceDAO(digital)

        digitalResourceService.addOneDigitalResource(digitalDAO)

        var dia = Date(0);

        val exhibitionDTO1 = ExhibitionDTO(0, editorDTO, emptyList(),"","", digital, emptyList(),dia,
            Status.PRIVATE, mutableListOf(),
            emptyList())

        val exhibitionDTO2 = ExhibitionDTO(0, editorDTO, emptyList(),"","", digital, emptyList(),dia,Status.PRIVATE,
            mutableListOf(),
            emptyList())

        val exhibitionDTOList = listOf(exhibitionDTO1, exhibitionDTO2)

        val exhibitionDAO1 = ExhibitionDAO(exhibitionDTO1)

        val exhibitionDAO2 = ExhibitionDAO(exhibitionDTO2)

        //exhibitionService.createExhibition(exhibitionDAO1)
        //exhibitionController.createExhibition(exhibitionDTO1)

        val exhibitionDAOList = listOf(exhibitionDAO1, exhibitionDAO2)

        print("\n\n\t( ͡o ͜ʖ ͡o) Database Seed Completed (╯ ͠° ͟ʖ ͡°)╯┻━┻\n")

    }
}