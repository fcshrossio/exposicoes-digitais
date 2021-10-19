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
    private exhibitionService: ExhibitionService
  ) { }

  ngOnInit(): void {
  }

  onSubmit(){
      this.exhibitionChange.emit(this.exhibition)
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
 

}
