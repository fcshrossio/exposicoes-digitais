import { Component, Input, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionItem } from 'src/app/model/exhibitionItem';
import { ExhibitionService } from 'src/app/service/exhibition.service';


@Component({
  selector: 'app-section-form',
  templateUrl: './section-form.component.html',
  styleUrls: ['./section-form.component.css']
})
export class SectionFormComponent implements OnInit {


  choosenSection = 0;

 
  @Input() exhibition? : Exhibition

  htmlContent : any

  constructor(
    private exhibitionService: ExhibitionService
  ) { }

  ngOnInit(): void {
    // var section1 = new ExhibitionItem(1,0,"introduction","As razões da festa","")
    // var section2 = new ExhibitionItem(2,0,"text","As outras razões da festa","")
    // var section3 = new ExhibitionItem(3,0,"text","","")
    // this.sections.push(section1)
    // this.sections.push(section2)
    // this.sections.push(section3)
    this.exhibition = this.exhibitionService.getSessionExhibition()
  }

  changeSection(id : number) {
    this.choosenSection = id-1
  }

  addSection() {
    if(this.exhibition)
    this.exhibition.items.push(new ExhibitionItem(this.exhibition.items.length+1,0,"","",""))
  }

}
