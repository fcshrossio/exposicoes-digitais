package com.rossio.exhibitions.config

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
    val exhibitionSubItemService: ExhibitionSubItemService,
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

        /**
        var digital = DigitalResourceDTO(
            0,
            "Título",
            "Descrição",
            "Cinemateca",
            Date(0),
            "Descrição fisica do objecto",
            "autores",
            "assunto",
            "",
            ""
        )
        **/
        var digital1 = DigitalResourceDTO(
            0,
            "Avenida Infante Dom Henrique",
            "",
            "Lisboa. Arquivo Municipal",
            "1999",
            "28 x 35 cm Prova cromogénea plastificada",
            listOf("Letria, Pedro. 1965-, fotógrafo"),
            "assunto",
            listOf("Documento original não comunicável.","Direitos reservados. Reprodução mediante autorização do proprietário da imagem."),
            "Image",
            "../../../../../assets/images/0001_M2.jpg"
        )

        var digital2 = DigitalResourceDTO(
            0,
            "A Rainha Dona Amélia e Dom Luís Filipe na festa de Santa Eufémia, promovida pelos empregados do palácio da Pena",
            "",
            "Lisboa. Arquivo Municipal",
            "1904-08-07",
            "Dimensão: 9 x 12 cmSuporte: Negativo de gelatina e prata em nitrato de celulose",
            listOf("Novais, António. 1854-1940, fotógrafo"),
            "assunto",
            listOf("Documento original não comunicável.","Direitos reservados. Reprodução mediante autorização do proprietário da imagem."),
            "Image",
            "../../../../../assets/images/0001_M2.jpg"
        )

        var digital3 = DigitalResourceDTO(
            0,
            "A Rainha Dona Amélia e Dom Luís Filipe na festa de Santa Eufémia, promovida pelos empregados do palácio da Pena",
            "",
            "Lisboa. Arquivo Municipal",
            "1904-08-07",
            "Dimensão: 9 x 12 cmSuporte: Negativo de gelatina e prata em nitrato de celulose",
            listOf("Novais, António. 1854-1940, fotógrafo"),
            "assunto",
            listOf("Documento original não comunicável.","Direitos reservados. Reprodução mediante autorização do proprietário da imagem."),
            "Image",
            "../../../../../assets/images/0001_M2.jpg"
        )


        val digitalDAO1 = digitalResourceService.addOneDigitalResource(DigitalResourceDAO(digital1))
        val digitalDAO2 = digitalResourceService.addOneDigitalResource(DigitalResourceDAO(digital2))
        val digitalDAO3 = digitalResourceService.addOneDigitalResource(DigitalResourceDAO(digital3))

        digital1 = DigitalResourceDTO(digitalDAO1)
        digital2 = DigitalResourceDTO(digitalDAO2)

        val exhibitionDTO1 = ExhibitionDTO(
            0, //id
            editorDTO, //editor
            mutableListOf(), //items
            "A Festa",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu hendrerit augue. Donec vehicula tristique augue id semper. Sed sed dictum magna, et laoreet sit.",
            "15",
            "",
            digital2, //cover
            mutableListOf(), //collaborator list
            Date(), // creation date
            Status.PUBLISHED, //status
            mutableListOf(),  //list of keywords
            "<b>Conce&#231;&#227;o geral</b></div><div> Phasellus eget efficitur leo. Aenean scelerisque felis sit amet sagittis interdum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</div><div><br></div><div><b>Coordena&#231;&#227;o cient&#237;fica</b></div><div> Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse potenti.</div><div><br></div><div><b>Equipa de investiga&#231;&#227;o</b><br></div><div> Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse potenti.<br>",
            "",
            "",
            "",
            ""
        )

        val exhibitionDTO2 = ExhibitionDTO(
            0, //id
            editorDTO, //editor
            mutableListOf(), //items
            "A música somos nós",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu hendrerit augue. Donec vehicula tristique augue id semper. Sed sed dictum magna, et laoreet sit.",
            "20",
            "",
            digital1, //cover
            mutableListOf(), //collaborator list
            Date(), // creation date
            Status.FORAPPROVAL, //status
            mutableListOf(),  //list of keywords
            "",
            "",
            "",
            "",
            ""
        )

        val exhibitionDTO3 = ExhibitionDTO(
            0, //id
            editorDTO, //editor
            mutableListOf(), //items
            "Cinema Portugues",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu hendrerit augue. Donec vehicula tristique augue id semper. Sed sed dictum magna, et laoreet sit.",
            "30",
            "",
            digital1, //cover
            mutableListOf(), //collaborator list
            Date(), // creation date
            Status.DRAFT, //status
            mutableListOf(),  //list of keywords
            "",
            "",
            "",
            "",
            ""

        )

        val exhibitionDTO4 = ExhibitionDTO(
            0, //id
            editorDTO, //editor
            mutableListOf(), //items
            "Cinema Portugues",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu hendrerit augue. Donec vehicula tristique augue id semper. Sed sed dictum magna, et laoreet sit.",
            "10",
            "",
            digital1, //cover
            mutableListOf(), //collaborator list
            Date(), // creation date
            Status.DRAFT, //status
            mutableListOf(),  //list of keywords
            "",
            "",
            "",
            "",
            ""
        )

        var exhibitionDAO1:ExhibitionDAO = exhibitionService.createExhibition(ExhibitionDAO(exhibitionDTO1, editorDAO, digitalDAO1))
        var exhibitionDAO2:ExhibitionDAO = exhibitionService.createExhibition(ExhibitionDAO(exhibitionDTO2, editorDAO, digitalDAO1))
        var exhibitionDAO3:ExhibitionDAO = exhibitionService.createExhibition(ExhibitionDAO(exhibitionDTO3, editorDAO, digitalDAO1))
        var exhibitionDAO4:ExhibitionDAO = exhibitionService.createExhibition(ExhibitionDAO(exhibitionDTO3, editorDAO, digitalDAO1))

        var item1DAO : ExhibitionItemDAO = ExhibitionItemDAO(0,1,"As razões e funções da festa", mutableListOf())
        var item2DAO : ExhibitionItemDAO =  ExhibitionItemDAO(0,2,"Os tempos da festa", mutableListOf())
        var item3DAO : ExhibitionItemDAO =  ExhibitionItemDAO(0,3,"A festa na sociedade e a sociedade na festa", mutableListOf())

        item1DAO = exhibitionItemService.createOneExhibitionItem(item1DAO)
        item2DAO = exhibitionItemService.createOneExhibitionItem(item2DAO)
        item3DAO = exhibitionItemService.createOneExhibitionItem(item3DAO)

        exhibitionDAO1 = exhibitionService.addExhibitionItem(exhibitionDAO1, item1DAO)
        exhibitionDAO1 = exhibitionService.addExhibitionItem(exhibitionDAO1, item2DAO)
        exhibitionService.addExhibitionItem(exhibitionDAO1, item3DAO)

        var subitem1DAO : SubItemDAO = SubItemDAO(0,0,"textresource", mutableListOf("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur auctor justo lorem. Vestibulum in commodo mauris, vel commodo lorem. Phasellus a suscipit quam. Proin pretium magna sed mollis dapibus. Curabitur cursus turpis in nunc lacinia, vel pellentesque nunc convallis. Duis imperdiet ullamcorper dui, sit amet imperdiet purus. Fusce ac tincidunt mi. Suspendisse potenti. Vivamus libero orci, molestie sit amet lacus vel, luctus lobortis augue. Curabitur eget pretium lorem, in euismod purus. Pellentesque volutpat eros ut urna elementum vestibulum. <p/>","<p>Vestibulum id tincidunt enim. Integer sit amet libero ac erat feugiat suscipit. Quisque tincidunt massa congue pharetra ultrices. Integer condimentum sem sit amet mauris consectetur, in sagittis elit iaculis. Proin ac sagittis orci, sit amet varius erat. Sed suscipit tortor justo, vitae facilisis elit tempus a. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse tincidunt volutpat ex quis hendrerit. Vestibulum pellentesque eu felis sit amet dictum. Nullam feugiat, lorem vitae ullamcorper dignissim, nulla sapien consectetur magna, sed laoreet erat erat at odio. Phasellus sit amet convallis odio. Cras ullamcorper turpis ac mauris feugiat, vel sagittis metus scelerisque. Aenean aliquet tempus justo, a venenatis ante luctus vel. </p>","o texto"), mutableListOf(digitalDAO2))
        var subitem2DAO : SubItemDAO = SubItemDAO(0,0,"resourcetext", mutableListOf("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur auctor justo lorem. Vestibulum in commodo mauris, vel commodo lorem. Phasellus a suscipit quam. Proin pretium magna sed mollis dapibus. Curabitur cursus turpis in nunc lacinia, vel pellentesque nunc convallis. Duis imperdiet ullamcorper dui, sit amet imperdiet purus. Fusce ac tincidunt mi. Suspendisse potenti. Vivamus libero orci, molestie sit amet lacus vel, luctus lobortis augue. Curabitur eget pretium lorem, in euismod purus. Pellentesque volutpat eros ut urna elementum vestibulum. <p/>","<p>Vestibulum id tincidunt enim. Integer sit amet libero ac erat feugiat suscipit. Quisque tincidunt massa congue pharetra ultrices. Integer condimentum sem sit amet mauris consectetur, in sagittis elit iaculis. Proin ac sagittis orci, sit amet varius erat. Sed suscipit tortor justo, vitae facilisis elit tempus a. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse tincidunt volutpat ex quis hendrerit. Vestibulum pellentesque eu felis sit amet dictum. Nullam feugiat, lorem vitae ullamcorper dignissim, nulla sapien consectetur magna, sed laoreet erat erat at odio. Phasellus sit amet convallis odio. Cras ullamcorper turpis ac mauris feugiat, vel sagittis metus scelerisque. Aenean aliquet tempus justo, a venenatis ante luctus vel. </p>","o texto"), mutableListOf(digitalDAO1))
        var subitem3DAO : SubItemDAO = SubItemDAO(0,0,"resource", mutableListOf(), mutableListOf(digitalDAO3))
        var subitem4DAO : SubItemDAO = SubItemDAO(0,0,"resourcefull", mutableListOf(), mutableListOf(digitalDAO3))

        subitem1DAO = exhibitionSubItemService.createSubItem(subitem1DAO)
        exhibitionItemService.addSubItem(item1DAO,subitem1DAO)
        subitem2DAO = exhibitionSubItemService.createSubItem(subitem2DAO)
        //exhibitionItemService.addSubItem(item1DAO,subitem2DAO)
        //subitem3DAO = exhibitionSubItemService.createSubItem(subitem3DAO)
        //exhibitionItemService.addSubItem(item3DAO,subitem3DAO)
        //subitem4DAO = exhibitionSubItemService.createSubItem(subitem4DAO)
        //exhibitionItemService.addSubItem(item3DAO,subitem4DAO)

        print("\n\n\t( ͡o ͜ʖ ͡o) Database Seed Completed (╯ ͠° ͟ʖ ͡°)╯┻━┻\n")

    }
}