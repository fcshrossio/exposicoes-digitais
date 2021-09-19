import { Component, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionItem} from 'src/app/model/exhibitionItem'
import { ExhibitionService } from 'src/app/service/exhibition.service';

@Component({
  selector: 'app-introduction-form',
  templateUrl: './introduction-form.component.html',
  styleUrls: ['./introduction-form.component.css']
})
export class IntroductionFormComponent implements OnInit {


  editableName: string = "FCG/BA - Teatro de São Carlos"

  file: any

  keywords: string[] = ["artes", "história"]


  exhibition? : Exhibition 


  constructor(
    private exhibitionService: ExhibitionService
  ) { }

  ngOnInit(): void {
    
    this.exhibition = this.exhibitionService.getSessionExhibition()
    if(this.exhibition.items.length == 0)
    {
      this.initializeIntroductionItem(this.exhibition)
    } 
    

    
  }

  initializeIntroductionItem(exhibition : Exhibition) {
    console.log("item introdução criado")
    var introductionItem = new ExhibitionItem(0,0,"introduction","Apresentação","")
    exhibition.keywords = this.keywords
    exhibition.items.push(introductionItem)
    this.exhibitionService.saveSessionExhibition(exhibition)
  }

  onFileDropped($event : any) {
    //console.log("on file dropped")
    for (const item of $event) {
      this.file = item;
    }
    console.log(this.file)
    if(this.file.name){
      this.editableName = this.file.name
    }
}

  removeKeyword( keywordRemove : string) {
    if(this.exhibition)
    {
      this.exhibition.keywords = this.exhibition.keywords.filter((keyword) => keywordRemove != keyword)
      //save
      this.exhibitionService.saveSessionExhibition(this.exhibition)
    }
  }

}
