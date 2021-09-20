import { Component, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';

@Component({
  selector: 'app-exhibition-list',
  templateUrl: './exhibition-list.component.html',
  styleUrls: ['./exhibition-list.component.css']
})
export class ExhibitionListComponent implements OnInit {

  exhibitions: Exhibition[] = [];

  selectedExhibition?: Exhibition

  getExhibitions(): void 
  {
   // this.exhibitionService.getExhibitions().subscribe( exhibitions => this.exhibitions = exhibitions)
  }


  constructor(private exhibitionService: ExhibitionService) { }

  ngOnInit(): void {
    this.getExhibitions();
  }

  onSelect(exhibition:Exhibition) : void {
    this.selectedExhibition = exhibition;
  }

}
