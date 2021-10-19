export class DigitalResource {
    id : number;
    title : string;
    description : string;
    dataProvider : string;
    date : string;
    physicalDescription : string;
    authors : string[];
    subject : string;
    rights : string[];
    digitalFormat : string;
    identifier : string

    constructor(
        id : number,
        title : string,
        description : string,
        dataProvider : string,
        date : string,
        physicalDescription : string,
        authors: string[],
        subject: string,
        rights: string[],
        digitalFormat: string,
        identifier: string
    ) {
        this.id = id;
        this.title = title
        this.description = description
        this.dataProvider =  dataProvider
        this.date = date
        this.physicalDescription = physicalDescription
        this.authors = authors
        this.subject = subject
        this.rights = rights
        this.digitalFormat = digitalFormat
        this.identifier = identifier
    }
}