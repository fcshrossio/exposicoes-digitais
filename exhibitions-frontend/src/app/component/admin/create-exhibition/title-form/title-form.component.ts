import { Component, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';

@Component({
  selector: 'app-title-form',
  templateUrl: './title-form.component.html',
  styleUrls: ['./title-form.component.css']
})
export class TitleFormComponent implements OnInit {


  exhibition? : Exhibition 

  constructor(
    private exhibitionService: ExhibitionService
  ) { }

  ngOnInit(): void {

    this.exhibition = this.exhibitionService.getSessionExhibition()
    if(!this.exhibition){
      this.initializeExhibition()
    }
  }

  initializeExhibition()
  {
    this.exhibition = new Exhibition("A festa", "Grande Feestona","editor")
    this.exhibitionService.createSessionExhibition(this.exhibition)
   
  }

  onChangeTitle(newTitle: string){
    if(this.exhibition){
      this.exhibition.title = newTitle
      this.exhibitionService.saveSessionExhibition(this.exhibition)
    }
    
  }

}
