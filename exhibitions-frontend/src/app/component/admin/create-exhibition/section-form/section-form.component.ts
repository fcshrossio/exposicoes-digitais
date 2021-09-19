import { Component, OnInit } from '@angular/core';
import { ExhibitionItem } from 'src/app/model/exhibitionItem';

@Component({
  selector: 'app-section-form',
  templateUrl: './section-form.component.html',
  styleUrls: ['./section-form.component.css']
})
export class SectionFormComponent implements OnInit {


  sections: ExhibitionItem[] = []

  exhibitionItemName : string = ""

  choosenSection = 0;

  constructor() { }

  ngOnInit(): void {
    var section1 = new ExhibitionItem(1,0,"","As razões da festa","")
    var section2 = new ExhibitionItem(2,0,"","As outras razões da festa","")
    var section3 = new ExhibitionItem(3,0,"","","")
    this.sections.push(section1)
    this.sections.push(section2)
    this.sections.push(section3)
  }

  changeSection(id : number) {
    console.log("this is a click on " + id)
    this.choosenSection = id-1
  }

}
