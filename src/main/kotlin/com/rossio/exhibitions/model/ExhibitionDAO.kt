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
    var estimatedViewingTime: String,
    @Column( length = 100000 )
    var introduction: String,
    @ManyToOne
    @JoinColumn
    var cover: DigitalResourceDAO,
    var coverEditable : String,
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
    @Column( length = 100000 )
    var onlineResourcesNova : String,
    @Column( length = 100000 )
    var bibliography : String,
    @Column( length = 100000 )
    var audiovisualResources: String,
    @Column( length = 100000 )
    var webPlaces: String,
    @OneToMany
    var markers : MutableList<MarkerDAO>
) {

    constructor(exhibition : ExhibitionDTO, editor: EditorDAO, cover: DigitalResourceDAO ) : this(
        exhibition.id,
        editor,
        exhibition.items.map{ ExhibitionItemDAO(it) } as MutableList<ExhibitionItemDAO>,
        exhibition.title,
        exhibition.subtitle,
        exhibition.estimatedViewingTime,
        exhibition.introduction,
        cover,
        exhibition.coverEditable,
        exhibition.collaborators.map{ CollaboratorDAO(it)} as MutableList<CollaboratorDAO>,
        exhibition.creationDate,
        exhibition.status,
        exhibition.keywords,
        exhibition.credits,
        exhibition.onlineResourcesNova,
        exhibition.bibliography,
        exhibition.audiovisualResources,
        exhibition.webPlaces,
        exhibition.markers.map{ MarkerDAO(it)} as MutableList<MarkerDAO>
    )

    constructor() : this(0, EditorDAO(), mutableListOf(), "","","","", DigitalResourceDAO(),"", mutableListOf(),Date(),Status.DRAFT,
        mutableListOf(),"","","","","",
        mutableListOf()) {

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
            this.estimatedViewingTime = detailsDTO.estimatedViewingTime
            this.cover = DigitalResourceDAO(detailsDTO.cover)
            this.coverEditable = detailsDTO.coverEditable
            this.introduction = detailsDTO.introduction
            this.keywords = detailsDTO.keywords
            this.onlineResourcesNova = detailsDTO.onlineResourcesNova
            this.bibliography = detailsDTO.bibliography
            this.audiovisualResources = detailsDTO.audiovisualResources
            this.webPlaces = detailsDTO.webPlaces
            this.status = detailsDTO.status
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

    fun addExhibitionMarker(markerDAO: MarkerDAO)
    {
        if(!markers.contains(markerDAO))
        {
            markers.add(markerDAO)
        }
        else
        {
            throw NotFoundException("marker already exists")
        }

    }

    fun removeExhibitionMarker(markerDAO: MarkerDAO)
    {
        if(markers.contains(markerDAO))
        {
            markers.remove(markerDAO)
        } else
        {
            throw NotFoundException("sub item does not exist")
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


