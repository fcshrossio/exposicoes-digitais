import { Component, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';

@Component({
  selector: 'app-create-exhibition',
  templateUrl: './create-exhibition.component.html',
  styleUrls: ['./create-exhibition.component.css']
})
export class CreateExhibitionComponent implements OnInit {

  constructor(
    private exhibitionService: ExhibitionService
  ) { }

  ngOnInit(): void {
  }


  clearExhibition() {
      console.log("clear exhibition")
      this.exhibitionService.clearSessionExhibition()
  }

  createExhibition() {
    console.log("create exhibition")
    var exhibition: Exhibition = this.exhibitionService.getSessionExhibition()
    this.exhibitionService.createExhibition(exhibition)
  }

  saveExhibitionAsDraft(){
    console.log("save exhibition as draft")
  }

  previewExhibition(){
    console.log("preview exhibition")
    console.log(this.exhibitionService.getSessionExhibition())
  }

  publishExhibition(){
    console.log("publish exhibition")
  }

}
