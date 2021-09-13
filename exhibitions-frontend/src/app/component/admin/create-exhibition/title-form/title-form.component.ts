import { Component, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';

@Component({
  selector: 'app-title-form',
  templateUrl: './title-form.component.html',
  styleUrls: ['./title-form.component.css']
})
export class TitleFormComponent implements OnInit {

  title : string = "A festa"

  description : string = "Grande Festona"

  exhibition? : Exhibition 

  constructor(
    private exhibitionService: ExhibitionService
  ) { }

  ngOnInit(): void {
    this.exhibition = new Exhibition("A festa", "Grande Feestona","editor")
    this.exhibitionService.createSessionExhibition(this.exhibition)
    this.exhibition = this.exhibitionService.getSessionExhibition()
  }

}
