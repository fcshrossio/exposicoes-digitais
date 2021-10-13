import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';
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

  keywords: string[] = ["Teste1", "Teste2"]


  @Input() exhibition? : Exhibition 

  @Output() exhibitionChange:EventEmitter<Exhibition> = new EventEmitter<Exhibition>()


  constructor(
  ) { }

  ngOnInit(): void {
  }

  
  onSubmit(){
    this.exhibitionChange.emit(this.exhibition)
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
    }
  }



}
