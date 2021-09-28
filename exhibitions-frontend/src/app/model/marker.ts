

export class Marker {
    id : number;
    coordinates : string;
    title : string;

    constructor(
        id:number,
        coordinates: string,
        title : string
    ) {
        this.id = id;
        this.coordinates = coordinates;
        this.title = title;
    }
}