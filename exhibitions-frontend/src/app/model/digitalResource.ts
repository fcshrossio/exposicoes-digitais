export class DigitalResource {
    id : number;
    title : string;
    description : string;
    dataProvider : string;
    date : Date;
    physicalDescription : string;
    authors : string;
    subject : string;

    constructor(
        id : number,
        title : string,
        description : string,
        dataProvider : string,
        date : Date,
        physicalDescription : string,
        authors: string,
        subject: string
    ) {
        this.id = id;
        this.title = title
        this.description = description
        this.dataProvider =  dataProvider
        this.date = date
        this.physicalDescription = physicalDescription
        this.authors = authors
        this.subject = subject
    }
}