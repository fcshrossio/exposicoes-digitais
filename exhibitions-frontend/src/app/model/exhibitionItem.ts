export class ExhibitionItem {

    id : number;
    exhibitionId: number;
    itemType: string;
    title:string;
    text: string;

    constructor(
        id: number,
        exhibitionId: number,
        itemType: string,
        title:string,
        text: string
    ) {

        this.id = id;
        this.exhibitionId = exhibitionId;
        this.title = title
        this.text = text;
        this.itemType = itemType
    }
}