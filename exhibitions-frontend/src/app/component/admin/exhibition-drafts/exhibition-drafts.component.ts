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

  collaborations: Exhibition[] = []

  drafts: Exhibition[] = []

  constructor(private exhibitionService: ExhibitionService,) { }

  ngOnInit(): void {
    this.getExhibitions()

    console.log(this.drafts)
  }


  getExhibitions(): void 
  {
    this.exhibitionService.getExhibitions().subscribe( exhibitions => { 
      this.exhibitions = exhibitions 
      this.drafts = this.exhibitions.filter( (element) => { return element.status === "DRAFT" })
      this.collaborations = this.exhibitions.filter( (element) => { return element.collaborators.length >= 1 })
    })
  }

  
  changeSelection( selection : string){
    this.selection = selection
  }


}
