import { Component, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';

@Component({
  selector: 'app-exhibition-list-homepage',
  templateUrl: './exhibition-list-homepage.component.html',
  styleUrls: ['./exhibition-list-homepage.component.css']
})
export class ExhibitionListHomepageComponent implements OnInit {
  
  exhibitions: Exhibition[] = [];

  getExhibitions(): void 
  {
    this.exhibitionService.getExhibitions().subscribe( exhibitions => this.exhibitions = exhibitions)
  }


  constructor(private exhibitionService: ExhibitionService) { }

  ngOnInit(): void {
    this.getExhibitions();
  }

}
