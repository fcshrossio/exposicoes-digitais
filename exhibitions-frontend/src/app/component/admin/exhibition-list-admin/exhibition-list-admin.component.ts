import { Component, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';

@Component({
  selector: 'app-exhibition-list-admin',
  templateUrl: './exhibition-list-admin.component.html',
  styleUrls: ['./exhibition-list-admin.component.css']
})
export class ExhibitionListAdminComponent implements OnInit {

  exhibitions: Exhibition[] = []

  constructor(private exhibitionService: ExhibitionService) { }

  ngOnInit(): void {
      this.getExhibitions()
      
  }

  getExhibitions(): void 
  {
    this.exhibitionService.getExhibitions().subscribe( exhibitions => this.exhibitions = exhibitions)
  }


}
