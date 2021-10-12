import { Component, Input, OnInit, Output } from '@angular/core';
import { DigitalResource } from 'src/app/model/digitalResource';
import { Editor } from 'src/app/model/editor';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-title-form',
  templateUrl: './title-form.component.html',
  styleUrls: ['./title-form.component.css']
})
export class TitleFormComponent implements OnInit {


  @Input() exhibition? : Exhibition 

  @Output() exhibitionChange:EventEmitter<Exhibition> = new EventEmitter<Exhibition>()

  constructor(
    //private exhibitionService: ExhibitionService
  ) { }

  ngOnInit(): void {
    //this.exhibition = new Exhibition("","", new Editor(0,"Marco"))
  }


  /** 
  onChangeTitle(newTitle: string){
    if(this.exhibition){
      this.exhibition = this.exhibitionService.getSessionExhibition()
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

  */

  onSubmit(){
      this.exhibitionChange.emit(this.exhibition)
  }

}
