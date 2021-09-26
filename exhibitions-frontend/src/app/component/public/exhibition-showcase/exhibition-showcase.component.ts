import { Component, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';

@Component({
  selector: 'app-exhibition-showcase',
  templateUrl: './exhibition-showcase.component.html',
  styleUrls: ['./exhibition-showcase.component.css']
})
export class ExhibitionShowcaseComponent implements OnInit {

  exhibition? : Exhibition

  selectedSection?: any 

  constructor(
    private exhibitionService: ExhibitionService
    ) { }

  ngOnInit(): void {
  }

  getExhibitions(): void 
  {
    this.exhibitionService.getExhibition(5).subscribe( exhibition => this.exhibition = exhibition)
  }


}
