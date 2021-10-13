import { Component, Input, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionItem } from 'src/app/model/exhibitionItem';
import { ExhibitionSubItem } from 'src/app/model/exhibitionSubItem';
import { ExhibitionService } from 'src/app/service/exhibition.service';


@Component({
  selector: 'app-section-form',
  templateUrl: './section-form.component.html',
  styleUrls: ['./section-form.component.css']
})
export class SectionFormComponent implements OnInit {

  sectionLimit = 30;

  choosenSection = 0;

  availableSections = new Array(this.sectionLimit-1)
 
  @Input() exhibition? : Exhibition

  subsections : ExhibitionSubItem[] = []

  htmlContent : any

  constructor(
  ) { }

  ngOnInit(): void {
  }

  changeSection(id : number) {
    this.choosenSection = id-1
  }

  addSection() {
    if(this.exhibition && this.exhibition.items.length < this.sectionLimit)
    this.exhibition.items.push(new ExhibitionItem(this.exhibition.items.length+1,0,"","",""))
    this.availableSections.pop()
  }

  addSubSection(type : string){
    if(this.exhibition)
    {
        var subsection = new ExhibitionSubItem(0,this.exhibition.id,0,this.subsections.length,type)
        this.subsections.push( subsection)
        
    }
  }

}
