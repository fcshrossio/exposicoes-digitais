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
  
  exhibition: Exhibition = new Exhibition("", "",new Editor(2,"Marco"))

  step = 0

  constructor(
    private exhibitionService: ExhibitionService
  ) { }

  ngOnInit(): void {
 
    if(this.exhibition.id == 0)
    {
      this.initializeExhibition()
    }
    // this.exhibition = this.exhibitionService.getSessionExhibition()
    // if(!this.exhibition){
    //   this.initializeExhibition()
    // }
  }

  initializeExhibition()
  {
    var editor = new Editor(2,"Marco")
    this.exhibition = new Exhibition("", "",editor)
    var cover = new DigitalResource(4,"NOME","","","", "",[],"",[],"","")
    this.exhibition.addCoverPhoto(cover)
    //this.exhibitionService.createSessionExhibition(this.exhibition)
   
  }


  clearExhibition() {
      console.log("clear exhibition")
      this.exhibitionService.clearSessionExhibition()
  }

  createExhibition(){
    if(this.exhibition){
      this.exhibitionService.createExhibition(this.exhibition).subscribe(
        exhibition => { 
          console.log( "exhibition posted: " + exhibition.id)
          this.exhibition = exhibition
      })
    }
  }

  saveExhibitionAsDraft(){
    console.log("save exhibition as draft")
    this.exhibitionService.updateExhibition(this.exhibition).subscribe(
    exhibition => { 
         this.exhibition = exhibition
         console.log( "exhibition details updated: " + exhibition)
       }
     )
    console.log(this.exhibition)
    this.exhibitionService.updateExhibitionCredits(this.exhibition).subscribe(
      exhibition => { 
        console.log( "exhibition credits updated: " + exhibition)
      }
    )
  }

  previewExhibition(){
    console.log("preview exhibition")
    console.log(this.exhibition)
  }

  publishExhibition(){
    console.log("publish exhibition")
  }

  changeStep(step: number){
    this.step = step
  }

}
