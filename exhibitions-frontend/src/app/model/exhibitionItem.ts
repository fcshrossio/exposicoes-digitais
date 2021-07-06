export class ExhibitionItem {

    id : number;
    exhibitionId: number;
    text: string;

    constructor(
        id: number,
        exhibitionId: number,
        text: string,
    ) {

        this.id = id;
        this.exhibitionId = exhibitionId;
        this.text = text;
    }
}