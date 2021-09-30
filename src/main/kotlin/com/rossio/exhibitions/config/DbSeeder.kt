package com.rossio.exhibitions.config

import com.rossio.exhibitions.controller.ExhibitionController
import com.rossio.exhibitions.controller.ExhibitionItemController
import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.enums.Status
import com.rossio.exhibitions.model.*
import com.rossio.exhibitions.service.*
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.*

@Component
class DbSeeder (
    val exhibitionService: ExhibitionService,
    val exhibitionItemService: ExhibitionItemService,
    val exhibitionItemController: ExhibitionItemController,
    val editorService: EditorService,
    val adminService: AdminService,
    val collaboratorService: CollaboratorService,
    val digitalResourceService: DigitalResourceService,
) : CommandLineRunner {
    val runSeeder = true

    override fun run(vararg args: String?) {
        if (!runSeeder) {
            print("Database seeder disabled.")
            return
        }

        fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

        var user1DTO = UserPasswordDTO("Henrique",passwordEncoder().encode("admin"))

        var user2DTO = UserPasswordDTO("Marco",passwordEncoder().encode("admin"))

        var user3DTO = UserPasswordDTO("Henrique Colaborador",passwordEncoder().encode("admin"))

        var adminDAO = AdminDAO(user1DTO)

        var editorDAO = EditorDAO(user2DTO)

        var collaboratorDAO = CollaboratorDAO(user3DTO)

        adminService.addOneAdmin(adminDAO)

         editorDAO = editorService.addOneEditor(editorDAO)
         var editorDTO = EditorDTO(editorDAO)

        collaboratorService.addOneCollaborator(collaboratorDAO)

        var digital = DigitalResourceDTO(0,"Título", "Descrição", "Cinemateca", Date(0), "Descrição fisica do objecto", "autores", "assunto")

        val digitalDAO = digitalResourceService.addOneDigitalResource(DigitalResourceDAO(digital))

        digital = DigitalResourceDTO(digitalDAO)

        val exhibitionDTO1 = ExhibitionDTO(
        0, //id
        editorDTO, //editor
        mutableListOf(), //items
        "A Festa",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu hendrerit augue. Donec vehicula tristique augue id semper. Sed sed dictum magna, et laoreet sit.",
        digital, //cover
        mutableListOf(), //collaborator list
        Date(), // creation date
        Status.PUBLIC, //status
        mutableListOf(),  //list of keywords
        mutableListOf()     //list of resources
        )

        val exhibitionDTO2 = ExhibitionDTO(
            0, //id
            editorDTO, //editor
            mutableListOf(), //items
            "A música somos nós",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu hendrerit augue. Donec vehicula tristique augue id semper. Sed sed dictum magna, et laoreet sit.",
            digital, //cover
            mutableListOf(), //collaborator list
            Date(), // creation date
            Status.PUBLIC, //status
            mutableListOf(),  //list of keywords
            mutableListOf()     //list of resources
        )

        val exhibitionDTO3 = ExhibitionDTO(
            0, //id
            editorDTO, //editor
            mutableListOf(), //items
            "Cinema Portugues",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu hendrerit augue. Donec vehicula tristique augue id semper. Sed sed dictum magna, et laoreet sit.",
            digital, //cover
            mutableListOf(), //collaborator list
            Date(), // creation date
            Status.PUBLIC, //status
            mutableListOf(),  //list of keywords
            mutableListOf()     //list of resources
        )

        val exhibitionDTO4 = ExhibitionDTO(
            0, //id
            editorDTO, //editor
            mutableListOf(), //items
            "Cinema Portugues",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu hendrerit augue. Donec vehicula tristique augue id semper. Sed sed dictum magna, et laoreet sit.",
            digital, //cover
            mutableListOf(), //collaborator list
            Date(), // creation date
            Status.PUBLIC, //status
            mutableListOf(),  //list of keywords
            mutableListOf()     //list of resources
        )

        var exhibitionDAO1:ExhibitionDAO = exhibitionService.createExhibition(ExhibitionDAO(exhibitionDTO1, editorDAO, digitalDAO))
        var exhibitionDAO2:ExhibitionDAO = exhibitionService.createExhibition(ExhibitionDAO(exhibitionDTO2, editorDAO, digitalDAO))
        var exhibitionDAO3:ExhibitionDAO = exhibitionService.createExhibition(ExhibitionDAO(exhibitionDTO3, editorDAO, digitalDAO))
        var exhibitionDAO4:ExhibitionDAO = exhibitionService.createExhibition(ExhibitionDAO(exhibitionDTO3, editorDAO, digitalDAO))

        var item1DAO : ExhibitionItemDAO = ExhibitionItemDAO(0,0,"As razões e funções da festa", "TEXT As razões e funções da festa", mutableListOf())
        var item2DAO : ExhibitionItemDAO =  ExhibitionItemDAO(0,0,"Os tempos da festa", "TEXT Os tempos da festa", mutableListOf())
        var item3DAO : ExhibitionItemDAO =  ExhibitionItemDAO(0,0,"A festa na sociedade e a sociedade na festa", "TEXT A festa na sociedade e a sociedade na festa", mutableListOf())
        item1DAO = exhibitionItemService.createOneExhibitionItem(item1DAO)
        item2DAO = exhibitionItemService.createOneExhibitionItem(item2DAO)
        item3DAO = exhibitionItemService.createOneExhibitionItem(item3DAO)
        exhibitionDAO1 = exhibitionService.addExhibitionItem(exhibitionDAO1, item1DAO)
        exhibitionDAO1 = exhibitionService.addExhibitionItem(exhibitionDAO1, item2DAO)
        exhibitionService.addExhibitionItem(exhibitionDAO1, item3DAO)


        print("\n\n\t( ͡o ͜ʖ ͡o) Database Seed Completed (╯ ͠° ͟ʖ ͡°)╯┻━┻\n")

    }
}