import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Exhibition } from 'src/app/model/exhibition';



import { ExhibitionService } from 'src/app/service/exhibition.service';

@Component({
  selector: 'app-exhibition-detail',
  templateUrl: './exhibition-detail.component.html',
  styleUrls: ['./exhibition-detail.component.css']
})
export class ExhibitionDetailComponent implements OnInit {

  exhibition : Exhibition | undefined

  constructor(
    private route: ActivatedRoute,
    private exhibitionService: ExhibitionService
  ) { }

  ngOnInit(): void {
    this.getExhibition();
  }

 

  getExhibition(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.exhibitionService.getExhibition(id).subscribe(exhibition => this.exhibition = exhibition)
  }

}
