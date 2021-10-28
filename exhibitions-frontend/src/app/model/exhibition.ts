import { Collaborator } from "./collaborator";
import { DigitalResource } from "./digitalResource";
import { Editor } from "./editor";
import { ExhibitionItem } from "./exhibitionItem";
import { Marker } from "./marker";

export class Exhibition {
    id : number;
    title : string;
    subtitle : string;
    estimatedViewingTime : string;
    introduction : string;
    cover : DigitalResource;
    coverEditable : string;
    editor : Editor;
    collaborators : Collaborator[];
    creationDate: Date;
    status: string;
    keywords: string[];
    items : ExhibitionItem[]
    credits: string;
    onlineResourcesNova: string;
    bibliography: string;
    audiovisualResources: string;
    webPlaces: string;
    markers : Marker[]

    constructor(
        title: string,
        subtitle: string,
        editor : Editor
    ) {
        this.id = 0;
        this.title = title
        this.subtitle = subtitle
        this.estimatedViewingTime = ""
        this.introduction = ""
        this.cover = new DigitalResource(4,"","","","","",[],"",[],"","")
        this.coverEditable = ""
        this.editor = editor
        this.collaborators = []
        this.creationDate = (new Date())
        this.status = "DRAFT"
        //this.keywords = ["Teste1", "Teste2"]
        this.keywords = []
        this.items = [
           // new ExhibitionItem(2,0,"introduction","texto","introduction"),
         
        ]
        this.credits = '',
        this.onlineResourcesNova = ""
        this.bibliography = ""
        this.audiovisualResources = ""
        this.webPlaces = ""
        this.markers = []
    }

    addCoverPhoto(digitalResource : DigitalResource) {
        this.cover = digitalResource
    }

    changeStatus(status : string){
        switch(status) {
            case "PRIVATE":
                this.status = status
                break;
            case "PUBLIC": 
                this.status= status
                break;
        }

    }
}