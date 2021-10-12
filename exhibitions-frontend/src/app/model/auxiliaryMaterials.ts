export class AuxiliaryMaterials {
    id : number;
    exhibitionId: number;
    onlineResourcesNova: string;
    bibliography: string;
    audiovisualResources: string;
    webPlaces: string;

    constructor(
        exhibitionId : number
    ) {
        this.id = 0;
        this.exhibitionId = exhibitionId
        this.onlineResourcesNova = ""
        this.bibliography = ""
        this.audiovisualResources = ""
        this.webPlaces = ""
    }

}