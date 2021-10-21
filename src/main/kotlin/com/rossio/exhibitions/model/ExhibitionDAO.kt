package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.enums.Keywords
import com.rossio.exhibitions.enums.Status
import com.rossio.exhibitions.exception.NotFoundException
import java.util.*
import javax.persistence.*

@Entity
data class ExhibitionDAO(
    @Id
    @GeneratedValue
    var id: Long,
    @ManyToOne
    @JoinColumn
    var editor : EditorDAO,
    @OneToMany
    var items : MutableList<ExhibitionItemDAO>,
    var title: String,
    var subtitle: String,
    var introduction: String,
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
    @Column( length = 100000 )
    var credits : String,
    var onlineResourcesNova : String,
    var bibliography : String,
    var audiovisualResources: String,
    var webPlaces: String
) {

    constructor(exhibition : ExhibitionDTO, editor: EditorDAO, cover: DigitalResourceDAO ) : this(
        exhibition.id,
        editor,
        exhibition.items.map{ ExhibitionItemDAO(it) } as MutableList<ExhibitionItemDAO>,
        exhibition.title,
        exhibition.subtitle,
        exhibition.introduction,
        cover,
        exhibition.collaborators.map{ CollaboratorDAO(it)} as MutableList<CollaboratorDAO>,
        exhibition.creationDate,
        exhibition.status,
        exhibition.keywords,
        exhibition.credits,
        exhibition.onlineResourcesNova,
        exhibition.bibliography,
        exhibition.audiovisualResources,
        exhibition.webPlaces
    )

    constructor() : this(0, EditorDAO(), mutableListOf(), "","","", DigitalResourceDAO(), mutableListOf(),Date(),Status.PRIVATE,
        mutableListOf(),"","","","","") {

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

            this.onlineResourcesNova = detailsDTO.onlineResourcesNova
            this.bibliography = detailsDTO.bibliography
            this.audiovisualResources = detailsDTO.audiovisualResources
            this.webPlaces = detailsDTO.webPlaces

            this.credits = detailsDTO.credits
            //TODO BETTER UPDATE FUNCTION
            return true
        }
        return false
    }



    fun addExhibitionItem(itemDAO: ExhibitionItemDAO)
    {
        if(!items.contains(itemDAO))
        {
            items.add(itemDAO)
        }
        else
        {
            throw NotFoundException("item already exists")
        }

    }

    fun editCredits( credits: String)
    {
        this.credits = credits
    }

    fun editAuxiliaryMaterials ( onlineResourcesNova : String, bibliography : String, audioVisualResources : String, webPlaces : String )
    {
        this.onlineResourcesNova = onlineResourcesNova
        this.bibliography = bibliography
        this.audiovisualResources = audioVisualResources
        this.webPlaces = webPlaces
    }
}


