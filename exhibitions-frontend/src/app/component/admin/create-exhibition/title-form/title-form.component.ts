import { Component, OnInit } from '@angular/core';
import { DigitalResource } from 'src/app/model/digitalResource';
import { Editor } from 'src/app/model/editor';
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
    var editor = new Editor(2,"Marco")
    this.exhibition = new Exhibition("", "",editor)
    var cover = new DigitalResource(4,"NOME")
    this.exhibition.addCoverPhoto(cover)
    this.exhibitionService.createSessionExhibition(this.exhibition)
   
  }

  onChangeTitle(newTitle: string){
    if(this.exhibition){
      this.exhibition.title = newTitle
      this.exhibitionService.saveSessionExhibition(this.exhibition)
    }
    
  }

  onChangeSubtitle(newTitle: string) {
    if(this.exhibition){
      this.exhibition.subtitle = newTitle
      this.exhibitionService.saveSessionExhibition(this.exhibition)
    }
        
  }

}
