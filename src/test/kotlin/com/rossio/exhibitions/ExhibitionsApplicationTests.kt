package com.rossio.exhibitions

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.rossio.exhibitions.dto.DigitalResourceDTO
import com.rossio.exhibitions.dto.EditorDTO
import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.dto.UserDTO
import com.rossio.exhibitions.enums.Status
import com.rossio.exhibitions.model.DigitalResourceDAO
import com.rossio.exhibitions.model.EditorDAO
import com.rossio.exhibitions.model.ExhibitionDAO
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class ExhibitionsApplicationTests {


    companion object {

        var uuid: Long = 0L;

        val mapper = jacksonObjectMapper()

        val userDTO = UserDTO(uuid++, "Henrique Raposo","password")

        var editorDAO = EditorDAO(userDTO)

        var editorDTO = EditorDTO(editorDAO)

        var digitalDAO = DigitalResourceDAO(uuid++, "NOME","", "", "", "", emptyList(), "", emptyList(), "", "" )

        val digitalDTO = DigitalResourceDTO(digitalDAO)

        var dia = Date(0);

        val exhibitionDTO1 = ExhibitionDTO(
            uuid++, editorDTO, emptyList(), "titulo", "subtitulo","", digitalDTO, emptyList(), dia,
            Status.PRIVATE, mutableListOf(),
            emptyList(),
            "",
            "",
            "",
            "",
            ""
        )

        val exhibitionDTO2 = ExhibitionDTO(
            uuid++,
            editorDTO,
            emptyList(),
            "titulo2",
            "suntitulo2",
            "",
            digitalDTO,
            emptyList(),
            dia,
            Status.PUBLIC,
            mutableListOf(),
            emptyList(),
            "",
            "",
            "",
            "",
            ""
        )

        val exhibitionDTOList = listOf(exhibitionDTO1, exhibitionDTO2)

        val exhibitionDAO1 = ExhibitionDAO(exhibitionDTO1, editorDAO, digitalDAO)

        val exhibitionDAO2 = ExhibitionDAO(exhibitionDTO2, editorDAO, digitalDAO)

        val exhibitionDAOList = listOf(exhibitionDAO1, exhibitionDAO2)
    }

    @Test
    fun contextLoads() {
    }

}
