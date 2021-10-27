import { DigitalResource } from "./digitalResource";

export class SavedResources {
    id : number;
    name : string
    digitalResources : DigitalResource[]

    constructor(
        id : number,
        name : string
    )
    {
        this.id = id
        this.name = name
        this.digitalResources = []
    }
}