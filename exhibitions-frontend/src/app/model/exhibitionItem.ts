import { ExhibitionSubItem } from "./exhibitionSubItem";

export class ExhibitionItem {

    id : number;
    exhibitionId: number;
    position: number;
    title:string;
    subItems: ExhibitionSubItem[]

    constructor(
        id: number,
        exhibitionId: number,
        position:number,
        title:string,
    ) {

        this.id = id;
        this.exhibitionId = exhibitionId;
        this.position = position;
        this.title = title
        this.subItems = []
    }
}