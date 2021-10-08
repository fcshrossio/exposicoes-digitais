import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';
import { isTemplateSpan } from 'typescript';

@Component({
  selector: 'app-exhibition-showcase',
  templateUrl: './exhibition-showcase.component.html',
  styleUrls: ['./exhibition-showcase.component.css']
})
export class ExhibitionShowcaseComponent implements OnInit {

  exhibition? : Exhibition

  exhibitions: Exhibition[] = []

  selectedSection: number = 0

  constructor(
    private route: ActivatedRoute,
    private exhibitionService: ExhibitionService
    ) { }

  ngOnInit(): void {
    this.getExhibition()
    this.getExhibitions()
  }

  getExhibition(): void 
  {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.exhibitionService.getExhibition(id).subscribe( exhibition => this.exhibition = exhibition)
  }

  getExhibitions(): void 
  {
    this.exhibitionService.getExhibitions().subscribe( exhibitions => this.exhibitions = exhibitions)
  }

  changeSection(selection:number): void
  {
    if(this.exhibition && selection < this.exhibition?.items.length)
    this.selectedSection = selection
    console.log(this.selectedSection)
  }

}
