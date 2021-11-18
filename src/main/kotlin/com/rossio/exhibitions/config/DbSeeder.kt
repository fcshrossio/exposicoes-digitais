package com.rossio.exhibitions.config

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
    val editorService: EditorService,
    val adminService: AdminService,
    val collaboratorService: CollaboratorService,
    val digitalResourceService: DigitalResourceService,
    val savedResourcesService: SavedResourcesService,
    val markerService: MarkerService
) : CommandLineRunner {
    val runSeeder = true

    override fun run(vararg args: String?) {
        if (!runSeeder) {
            print("Database seeder disabled.")
            return
        }

        fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

        var user1DTO = UserPasswordDTO("Henrique",passwordEncoder().encode("admin"))

        var user2DTO = UserPasswordDTO("Marco",passwordEncoder().encode("editor"))

        var user3DTO = UserPasswordDTO("Ana Silva",passwordEncoder().encode("editor"))

        var user4DTO = UserPasswordDTO("Henrique Colaborador",passwordEncoder().encode("admin"))

        var adminDAO = AdminDAO(user1DTO)

        var editorDAO = EditorDAO(user2DTO)

        var editor2DAO = EditorDAO(user3DTO)

        var collaboratorDAO1 = CollaboratorDAO(user4DTO)

        var collaboratorDAO2 = CollaboratorDAO(user3DTO)

        collaboratorService.addOneCollaborator(collaboratorDAO1)

        collaboratorService.addOneCollaborator(collaboratorDAO2)

        adminService.addOneAdmin(adminDAO)

         editorDAO = editorService.addOneEditor(editorDAO)

        editor2DAO = editorService.addOneEditor(editor2DAO)

         var editorDTO = EditorDTO(editorDAO)

        var editorDTO2 = EditorDTO(editor2DAO)



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

        var digital0 = DigitalResourceDTO(
            0,
            "placeholder",
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

        val digitalDAO0 = digitalResourceService.addOneDigitalResource(DigitalResourceDAO(digital0))

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

        val digitalDAO1 = digitalResourceService.addOneDigitalResource(DigitalResourceDAO(digital1))

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

        val digitalDAO2 = digitalResourceService.addOneDigitalResource(DigitalResourceDAO(digital2))
        digital2 = DigitalResourceDTO(digitalDAO2)

        var digital3 = DigitalResourceDTO(
            0,
            "Festas na avenida da Liberdade, Pauliteiros de Miranda",
            "Documento simples - Fotografia",
            "Lisboa. Arquivo Municipal",
            "1904-08-07",
            "Dimensão: 13 x 18 cmSuporte: Negativo de gelatina e prata em vidro",
            listOf("Guedes, Paulo. 1886-1947, fotógrafo"),
            "Ornamentação / Vestuário / Rancho folclórico / Festa / Liberdade (avenida, Santo António, Lisboa, Portugal)",
            listOf("Documento original não comunicável.","Direitos reservados. Reprodução mediante autorização do proprietário da imagem."),
            "Image",
            "../../../../../assets/images/0001_M.jpg"
        )

        val digitalDAO3 = digitalResourceService.addOneDigitalResource(DigitalResourceDAO(digital3))
        digital3 = DigitalResourceDTO(digitalDAO3)

        var digital4 = DigitalResourceDTO(
            0,
            "Festa a bordo de um cruzador americano. Lisboa",
            "",
            "DGLAB/ANTT",
            "1921-08-22",
            "1 doc. fotográfico (negativo, vidro, p/b, 9x12 cm)",
            listOf(" C. Garcês"),
            "assunto",
            listOf("Documento original não comunicável.","Direitos reservados. Reprodução mediante autorização do proprietário da imagem."),
            "Image",
            "../../../../../assets/images/PT-TT-EPJS-SF-006-00188_derivada.jpg"
        )

        val digitalDAO4 = digitalResourceService.addOneDigitalResource(DigitalResourceDAO(digital4))
        digital4 = DigitalResourceDTO(digitalDAO4)

        var digital5 = DigitalResourceDTO(
            0,
            "Castelo de Monsanto",
            "",
            "DGLAB/ANTT",
            "[entre 1950 e 1970]",
            "Dimensão: 6 x 6 cmSuporte: Diapositivo cromogéneo em acetato de celulose",
            listOf("Ferrari, Amadeu. 1909-1984, fotógrafo"),
            "Festa / Castelo de Monsanto / Monsanto (freguesia, Idanha-a-Nova, Castelo Branco, Portugal)",
            listOf("Direitos reservados para efeito de publicação, exposição e utilização comercial."),
            "Image",
            "../../../../../assets/images/0001_M3.jpg"
        )

        val digitalDAO5 = digitalResourceService.addOneDigitalResource(DigitalResourceDAO(digital5))
        digital5 = DigitalResourceDTO(digitalDAO5)

        var digital6 = DigitalResourceDTO(
            0,
            "Mouraria em festa",
            "",
            "DGLAB/ANTT",
            "[entre 1950 e 1970]",
            "Dimensão: 9 x 12 cmSuporte: Negativo de gelatina e prata em vidro",
            listOf("Bárcia, José Artur Leitão. 1873-1945, fotógrafo"),
            "Festa / Vestuário / Ornamentação / Capela de Nossa Senhora da Saúde / Martim Moniz (praça, Santa Maria Maior, Lisboa, Portugal)",
            listOf("Direitos reservados para efeito de publicação, exposição e utilização comercial."),
            "Image",
            "../../../../../assets/images/0001_M4.jpg"
        )

        val digitalDAO6 = digitalResourceService.addOneDigitalResource(DigitalResourceDAO(digital6))
        digital6 = DigitalResourceDTO(digitalDAO6)

        var digital7 = DigitalResourceDTO(
            0,
            "Organização das festas da cidade de Vila Real",
            "",
            "DGLAB/ANTT",
            "1936",
            "1 capilha, 13 f. (446-458); papel",
            listOf(""),
            "correspondência do Governador Civil de Vila Real, Horácio Assis Gonçalves relativa ao programa da Festa Oficial e Política do Ano",
            listOf(""),
            "Image",
            "../../../../../assets/images/PT-TT-AOS-D-G-9-1-3_m0001_derivada.jpg"
        )

        val digitalDAO7 = digitalResourceService.addOneDigitalResource(DigitalResourceDAO(digital7))
        digital7 = DigitalResourceDTO(digitalDAO7)

        digital1 = DigitalResourceDTO(digitalDAO1)
        digital2 = DigitalResourceDTO(digitalDAO2)

        val exhibitionDTO1 = ExhibitionDTO(
            0, //id
            UserSimpleDTO(editorDTO.id,editorDTO.username), //editor
            mutableListOf(), //items
            "A Festa",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu hendrerit augue. Donec vehicula tristique augue id semper. Sed sed dictum magna, et laoreet sit.",
            "15",
            "",
            digital2, //cover
            "",
            mutableListOf(UserDTO(collaboratorDAO2)), //collaborator list
            Date(), // creation date
            Status.PUBLISHED, //status
            mutableListOf(),  //list of keywords
            "<b>Conce&#231;&#227;o geral</b></div><div> Phasellus eget efficitur leo. Aenean scelerisque felis sit amet sagittis interdum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</div><div><br></div><div><b>Coordena&#231;&#227;o cient&#237;fica</b></div><div> Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse potenti.</div><div><br></div><div><b>Equipa de investiga&#231;&#227;o</b><br></div><div> Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse potenti.<br>",
            "",
            "",
            "",
            "",
            mutableListOf(),  //list of markers
        )

        val exhibitionDTO2 = ExhibitionDTO(
            0, //id
            UserSimpleDTO(editorDTO2.id,editorDTO2.username), //editor
            mutableListOf(), //items
            "A música somos nós",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu hendrerit augue. Donec vehicula tristique augue id semper. Sed sed dictum magna, et laoreet sit.",
            "20",
            "",
            digital3, //cover
            "",
            mutableListOf(), //collaborator list
            Date(), // creation date
            Status.FORAPPROVAL, //status
            mutableListOf(),  //list of keywords
            "",
            "",
            "",
            "",
            "",
            mutableListOf(),  //list of markers
        )

        val exhibitionDTO3 = ExhibitionDTO(
            0, //id
            UserSimpleDTO(editorDTO.id,editorDTO.username), //editor
            mutableListOf(), //items
            "Cinema Portugues",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu hendrerit augue. Donec vehicula tristique augue id semper. Sed sed dictum magna, et laoreet sit.",
            "30",
            "",
            digital4, //cover
            "",
            mutableListOf(), //collaborator list
            Date(), // creation date
            Status.DRAFT, //status
            mutableListOf(),  //list of keywords
            "",
            "",
            "",
            "",
            "",
            mutableListOf(),  //list of markers

        )

        val exhibitionDTO4 = ExhibitionDTO(
            0, //id
            UserSimpleDTO(editorDTO.id,editorDTO.username), //editor
            mutableListOf(), //items
            "Cinema Portugues",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu hendrerit augue. Donec vehicula tristique augue id semper. Sed sed dictum magna, et laoreet sit.",
            "10",
            "",
            digital5, //cover
            "",
            mutableListOf(), //collaborator list
            Date(), // creation date
            Status.DRAFT, //status
            mutableListOf(),  //list of keywords
            "",
            "",
            "",
            "",
            "",
            mutableListOf(),  //list of markers
        )

        var exhibitionDAO1:ExhibitionDAO = exhibitionService.createExhibition(ExhibitionDAO(exhibitionDTO1, editorDAO, digitalDAO2))
        var exhibitionDAO2:ExhibitionDAO = exhibitionService.createExhibition(ExhibitionDAO(exhibitionDTO2, editorDAO, digitalDAO1))
        var exhibitionDAO3:ExhibitionDAO = exhibitionService.createExhibition(ExhibitionDAO(exhibitionDTO3, editorDAO, digitalDAO3))
        var exhibitionDAO4:ExhibitionDAO = exhibitionService.createExhibition(ExhibitionDAO(exhibitionDTO3, editorDAO, digitalDAO4))

        var item1DAO : ExhibitionItemDAO = ExhibitionItemDAO(0,1,"As razões e funções da festa",exhibitionService.getOneExhibition(exhibitionDAO1.id), mutableListOf())

        var item2DAO : ExhibitionItemDAO =  ExhibitionItemDAO(0,2,"Os tempos da festa",exhibitionService.getOneExhibition(exhibitionDAO1.id), mutableListOf())
        var item3DAO : ExhibitionItemDAO =  ExhibitionItemDAO(0,3,"A festa na sociedade e a sociedade na festa",exhibitionService.getOneExhibition(exhibitionDAO1.id), mutableListOf())
        var item4DAO : ExhibitionItemDAO =  ExhibitionItemDAO(0,4,"O sagrado e o profano na festa",exhibitionService.getOneExhibition(exhibitionDAO1.id), mutableListOf())
        var item5DAO : ExhibitionItemDAO =  ExhibitionItemDAO(0,5,"Música e dança na festa",exhibitionService.getOneExhibition(exhibitionDAO1.id), mutableListOf())
        var item6DAO : ExhibitionItemDAO =  ExhibitionItemDAO(0,6,"As festas dos Santos Populares",exhibitionService.getOneExhibition(exhibitionDAO1.id), mutableListOf())

        item1DAO = exhibitionItemService.createOneExhibitionItem(item1DAO)
        item2DAO = exhibitionItemService.createOneExhibitionItem(item2DAO)
        item3DAO = exhibitionItemService.createOneExhibitionItem(item3DAO)
        item4DAO = exhibitionItemService.createOneExhibitionItem(item4DAO)
        item5DAO = exhibitionItemService.createOneExhibitionItem(item5DAO)
        item6DAO = exhibitionItemService.createOneExhibitionItem(item6DAO)




        var marker1DAO : MarkerDAO = MarkerDAO(0,exhibitionService.getOneExhibition(exhibitionDAO1.id), mutableListOf(-7.907558, 38.571703 ),"Évora");
        marker1DAO = markerService.createOneMarker(marker1DAO)

        var marker2DAO : MarkerDAO = MarkerDAO(0, exhibitionService.getOneExhibition(exhibitionDAO1.id), mutableListOf( -9.138574381126347,38.72219608332645),"Lisboa");
        marker2DAO = markerService.createOneMarker(marker2DAO)



        var subitem1DAO : SubItemDAO = SubItemDAO(0,0,item1DAO,"textresource", mutableListOf("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"), mutableListOf(digitalDAO3))
        var subitem2DAO : SubItemDAO = SubItemDAO(0,1,item1DAO,"resourcetext", mutableListOf("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"), mutableListOf(digitalDAO4))
        var subitem3DAO : SubItemDAO = SubItemDAO(0,2,item1DAO,"text", mutableListOf("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor"), mutableListOf())
        var subitem4DAO : SubItemDAO = SubItemDAO(0,3,item1DAO,"resourcefull", mutableListOf(), mutableListOf(digitalDAO5))


        subitem1DAO = exhibitionSubItemService.createSubItem(subitem1DAO)

        subitem2DAO = exhibitionSubItemService.createSubItem(subitem2DAO)

        subitem3DAO = exhibitionSubItemService.createSubItem(subitem3DAO)

        subitem4DAO = exhibitionSubItemService.createSubItem(subitem4DAO)

        var savedResourcesDAO1 = SavedResourcesDAO(0,"A festa", listOf(digitalDAO1,digitalDAO2,digitalDAO3),editorDAO)
        savedResourcesDAO1 = savedResourcesService.createSavedResources(savedResourcesDAO1)
        var savedResourcesDAO2 = SavedResourcesDAO(0,"A festa 2", listOf(digitalDAO4,digitalDAO5,digitalDAO6,digitalDAO7),editorDAO)
        savedResourcesDAO2 = savedResourcesService.createSavedResources(savedResourcesDAO2)


        print("\n\n\t( ͡o ͜ʖ ͡o) Database Seed Completed (╯ ͠° ͟ʖ ͡°)╯┻━┻\n")

    }
}