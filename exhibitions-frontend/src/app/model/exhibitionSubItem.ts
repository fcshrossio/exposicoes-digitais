import { DigitalResource } from "./digitalResource";

export class ExhibitionSubItem {

    id : number;
    exhibitionId: number;
    exhibitionItemId: number;
    position: number;
    itemType: string;
    textSections:string[];
    digitalResources: DigitalResource[];

    constructor(
        id: number,
        exhibitionId: number,
        exhibitionItemId: number,
        position: number,
        itemType: string
    ) {

        this.id = id;
        this.exhibitionId = exhibitionId;
        this.exhibitionItemId = exhibitionItemId;
        this.position = position
        this.textSections = []
        this.digitalResources = [];
        this.itemType = itemType
    }
}