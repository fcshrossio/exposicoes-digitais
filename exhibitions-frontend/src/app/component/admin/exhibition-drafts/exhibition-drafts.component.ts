import { Component, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';

@Component({
  selector: 'app-exhibition-drafts',
  templateUrl: './exhibition-drafts.component.html',
  styleUrls: ['./exhibition-drafts.component.css']
})
export class ExhibitionDraftsComponent implements OnInit {

  exhibitions: Exhibition[] = []

  selection: string = "all"

  constructor(private exhibitionService: ExhibitionService,) { }

  ngOnInit(): void {
    this.getExhibitions()
  }


  getExhibitions(): void 
  {
    this.exhibitionService.getExhibitions().subscribe( exhibitions => this.exhibitions = exhibitions)
  }


}
