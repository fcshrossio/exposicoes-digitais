

export class Marker {
    id : number;
    coordinates : number[];
    title : string;

    constructor(
        id:number,
        coordinates: number[],
        title : string
    ) {
        this.id = id;
        this.coordinates = coordinates;
        this.title = title;
    }
}