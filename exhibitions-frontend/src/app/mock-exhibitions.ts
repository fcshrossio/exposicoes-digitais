import { Exhibition } from "./model/exhibition";
import { ExhibitionItem } from "./model/exhibitionItem";




export var mockExhibitions:Exhibition[] = [

   new Exhibition("Título de Exposição Teste", "subtitulo de exposição teste", "editor")
]


export const mockExhibitionItems:ExhibitionItem[] = [
   new ExhibitionItem(2,0,"Lorem Ipsium"),
   new ExhibitionItem(3,1,"Lorem Ipsium")
]