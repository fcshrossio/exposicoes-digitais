package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.ExhibitionDTO
import java.util.*
import javax.persistence.*
import com.rossio.exhibitions.model.EditorDAO
import com.rossio.exhibitions.model.DigitalResourceDAO
import com.rossio.exhibitions.model.IntroductionItemDAO
import com.rossio.exhibitions.model.CollaboratorDAO
import org.hibernate.mapping.Collection

@Entity
data class ExhibitionDAO(
    @Id
    @GeneratedValue
    var id: Long,
    @ManyToOne
    @JoinColumn
    var editor : EditorDAO,
    @OneToMany(mappedBy = "exhibition")
    var items : MutableList<ExhibitionItemDAO>,
    var title: String,
    var subtitle: String,
    @ManyToOne
    @JoinColumn
    var cover: DigitalResourceDAO,
    @OneToMany
    var collaborators: MutableList<CollaboratorDAO>,
    var creationDate: Date,
    @Enumerated(EnumType.STRING)
    var status : Status,
    @ElementCollection
    @CollectionTable
    @Enumerated(EnumType.STRING)
    var keywords: MutableList<Keywords>,
    @OneToMany
    var digitalResources: List<DigitalResourceDAO>
) {
    constructor() : this(0, EditorDAO(), mutableListOf(),"","", DigitalResourceDAO(), mutableListOf(), Date(0), Status.PRIVATE, mutableListOf(), emptyList())

    constructor(exhibition : ExhibitionDTO, editor: EditorDAO, cover: DigitalResourceDAO ) : this(
        exhibition.id,
        editor,
        exhibition.items.map{ IntroductionItemDAO() } as MutableList<ExhibitionItemDAO>,
        exhibition.title,
        exhibition.subtitle,
        cover,
        exhibition.collaborators.map{ CollaboratorDAO(it)} as MutableList<CollaboratorDAO>,
        exhibition.creationDate,
        exhibition.status,
        exhibition.keywords,
        exhibition.digitalResources.map { DigitalResourceDAO(it)}
    )

    constructor(exhibition : ExhibitionDTO) : this(
        exhibition.id,
        EditorDAO(exhibition.editor),
        exhibition.items.map{ IntroductionItemDAO() } as MutableList<ExhibitionItemDAO>,
        exhibition.title,
        exhibition.subtitle,
        DigitalResourceDAO(exhibition.cover),
        exhibition.collaborators.map{ CollaboratorDAO(it)} as MutableList<CollaboratorDAO>,
        exhibition.creationDate,
        exhibition.status,
        exhibition.keywords,
        exhibition.digitalResources.map { DigitalResourceDAO(it)}
        )

    fun addCollaborator(collaboratorDAO: CollaboratorDAO)
    {
        if(!collaborators.contains(collaboratorDAO))
        {
            collaborators.add(collaboratorDAO)
        }
    }

    fun removeCollaborator(collaboratorDAO: CollaboratorDAO)
    {
        if(collaborators.contains(collaboratorDAO))
        {
            collaborators.remove(collaboratorDAO)
        }
    }

    fun addKeyword(keyword: Keywords)
    {
        if(!keywords.contains(keyword))
        {
            keywords.add(keyword)
        }
    }

    fun removeKeyword(keyword: Keywords)
    {
        if(keywords.contains(keyword))
        {
            keywords.remove(keyword)
        }
    }

    fun changeStatus(status: Status)
    {
        this.status = status
    }



    fun addExhibitionItem(itemDAO: ExhibitionItemDAO)
    {
        if(!items.contains(itemDAO))
        {
            if(itemDAO is IntroductionItemDAO)
            {
                if(items.isEmpty() || !items.any { it is IntroductionItemDAO })
                {
                    items.add(itemDAO)
                }
            }
            else
            {
                items.add(itemDAO)
            }

        }

    }


}



enum class Status {
    PUBLIC,
    PRIVATE
}

enum class Keywords {
    Teste1,
    Teste2
}