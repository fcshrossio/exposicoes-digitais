package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.enums.Keywords
import com.rossio.exhibitions.enums.Status
import com.rossio.exhibitions.exception.NotFoundException
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

    constructor(exhibition : ExhibitionDTO, editor: EditorDAO, cover: DigitalResourceDAO ) : this(
        exhibition.id,
        editor,
        exhibition.items.map{  } as MutableList<ExhibitionItemDAO>,
        exhibition.title,
        exhibition.subtitle,
        cover,
        exhibition.collaborators.map{ CollaboratorDAO(it)} as MutableList<CollaboratorDAO>,
        exhibition.creationDate,
        exhibition.status,
        exhibition.keywords,
        exhibition.digitalResources.map { DigitalResourceDAO(it)}
    )

    constructor() : this(0, EditorDAO(), mutableListOf(), "","", DigitalResourceDAO(), mutableListOf(),Date(),Status.PRIVATE,
        mutableListOf(), mutableListOf()) {

    }

    /*
    constructor(exhibition : ExhibitionDTO) : this(
        exhibition.id,
        EditorDAO(exhibition.editor),
        mutableListOf(),//exhibition.items.map{ IntroductionItemDAO() } as MutableList<ExhibitionItemDAO>,
        exhibition.title,
        exhibition.subtitle,
        DigitalResourceDAO(exhibition.cover),
        exhibition.collaborators.map{ CollaboratorDAO(it)} as MutableList<CollaboratorDAO>,
        exhibition.creationDate,
        exhibition.status,
        exhibition.keywords,
        exhibition.digitalResources.map { DigitalResourceDAO(it)}
        )

     */

    fun addCollaborator(collaboratorDAO: CollaboratorDAO): Boolean
    {
        if(!collaborators.contains(collaboratorDAO))
        {
            collaborators.add(collaboratorDAO)
            return true
        }
        return false
    }

    fun removeCollaborator(collaboratorDAO: CollaboratorDAO): Boolean
    {
        if(collaborators.contains(collaboratorDAO))
        {
            collaborators.remove(collaboratorDAO)
            return true
        }
        return false
    }

    fun addKeyword(keyword: Keywords): Boolean
    {
        if(!keywords.contains(keyword))
        {
            keywords.add(keyword)
            return true
        }
        return false
    }

    fun removeKeyword(keyword: Keywords): Boolean
    {
        if(keywords.contains(keyword))
        {
            keywords.remove(keyword)
            return true
        }
        return false
    }

    fun changeStatus(status: Status): Boolean
    {
        this.status = status
        return true
    }

    fun editDetails(detailsDTO: ExhibitionDTO): Boolean
    {
        if(detailsDTO.id == this.id) {
            this.title = detailsDTO.title
            this.subtitle = detailsDTO.subtitle
            //TODO BETTER UPDATE FUNCTION
            return true
        }
        return false
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


