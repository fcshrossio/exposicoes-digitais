import { Component, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';

@Component({
  selector: 'app-exhibition-page',
  templateUrl: './exhibition-page.component.html',
  styleUrls: ['./exhibition-page.component.css']
})
export class ExhibitionPageComponent implements OnInit {

  exhibitions: Exhibition[] = []

  images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);


  constructor(private exhibitionService: ExhibitionService) { }

  ngOnInit(): void {
      this.getExhibitions()
      
  }

  getExhibitions(): void 
  {
    this.exhibitionService.getExhibitions().subscribe( exhibitions => this.exhibitions = exhibitions)
  }

}
