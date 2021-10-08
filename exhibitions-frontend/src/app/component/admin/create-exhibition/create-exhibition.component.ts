import { Component, OnInit } from '@angular/core';
import { DigitalResource } from 'src/app/model/digitalResource';
import { Editor } from 'src/app/model/editor';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';

@Component({
  selector: 'app-create-exhibition',
  templateUrl: './create-exhibition.component.html',
  styleUrls: ['./create-exhibition.component.css']
})
export class CreateExhibitionComponent implements OnInit {
  
  exhibition?: Exhibition;

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
    var cover = new DigitalResource(4,"NOME","","","", "","","",[],[],"")
    this.exhibition.addCoverPhoto(cover)
    this.exhibitionService.createSessionExhibition(this.exhibition)
   
  }


  clearExhibition() {
      console.log("clear exhibition")
      this.exhibitionService.clearSessionExhibition()
  }

  createExhibition() {
    console.log("create exhibition")
    var exhibition: Exhibition = this.exhibitionService.getSessionExhibition()
    this.exhibitionService.createExhibition(exhibition).subscribe(
        exhibition => { 
          console.log( "exhibition posted: " + exhibition)
          if(exhibition) this.exhibitionService.saveSessionExhibition(exhibition)}
    )
    /** this overrides the session data with the server data, to retrieve the id */
  }

  saveExhibitionAsDraft(){
    console.log("save exhibition as draft")
    var exhibition: Exhibition = this.exhibitionService.getSessionExhibition()
    this.exhibitionService.updateExhibition(exhibition).subscribe(
      exhibition => { 
        console.log( "exhibition updated: " + exhibition)
      }
    )
  }

  previewExhibition(){
    console.log("preview exhibition")
    console.log(this.exhibitionService.getSessionExhibition())
  }

  publishExhibition(){
    console.log("publish exhibition")
  }

}
