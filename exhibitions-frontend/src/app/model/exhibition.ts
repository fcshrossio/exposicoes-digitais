import { mockExhibitionItems } from "../mock-exhibitions";
import { ExhibitionItem } from "./exhibitionItem";

export class Exhibition {
    id : number;
    title : string;
    subtitle : string;
    editor : string;
    creationDate: string;
    status: string;
    keywords: string;
    items : ExhibitionItem[]

    constructor(
        title: string,
        subtitle: string,
        editor : string
    ) {
        this.id = 0;
        this.title = title
        this.subtitle = subtitle
        this.editor = editor
        this.creationDate = (new Date()).toString()
        this.status = "PRIVATE"
        this.keywords = "keywords"
        this.items = [
            new ExhibitionItem(2,0,"texto"),
            new ExhibitionItem(2,0,"texto"),
            new ExhibitionItem(2,0,"texto"),
            new ExhibitionItem(2,0,"texto")
        ]
    }
}