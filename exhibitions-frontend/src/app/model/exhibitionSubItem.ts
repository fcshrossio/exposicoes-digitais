import { DigitalResource } from "./digitalResource";

export class ExhibitionSubItem {

    id : number;
    // exhibitionId: number;
    // exhibitionItemId: number;
    position: number;
    itemType: string;
    textSections:string[];
    digitalResources: DigitalResource[];

    constructor(
        id: number,
        position: number,
        itemType: string
    ) {
        this.id = id;

        this.position = position
        this.textSections = []
        this.digitalResources = [];
        this.itemType = itemType
        if(itemType == "text" || itemType == "textresource" || itemType == "resourcetext" )
        {
            this.textSections.push("")
        }
        if(itemType == "texttext")
        {
            this.textSections.push("")
            this.textSections.push("")
        }
        if(itemType == "resourceresource")
        {
            this.digitalResources.push()
            this.digitalResources.push()
        }
        // if(itemType == "resourcefull" || itemType == "resource" || itemType == "resourcetext" )
        // {
        //     this.digitalResources.push()
        // }
        

 
    }
}