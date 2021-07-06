import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionItem } from 'src/app/model/exhibitionItem';
import { ExhibitionItemService } from 'src/app/service/exhibition-item.service';
import { ExhibitionService } from 'src/app/service/exhibition.service';

@Component({
  selector: 'app-exhibition-edition',
  templateUrl: './exhibition-edition.component.html',
  styleUrls: ['./exhibition-edition.component.css']
})
export class ExhibitionEditionComponent implements OnInit {

  exhibition : Exhibition | undefined

  itemid : number | undefined
  
  item : ExhibitionItem | undefined

 

  constructor(
    private route: ActivatedRoute,
    private exhibitionService: ExhibitionService,
    private exhibitionItemService: ExhibitionItemService
  ) { }

  ngOnInit(): void {
    this.getExhibition();
    this.route.params.subscribe( params => {
      this.getExhibitionItem();
    })
  }


  getExhibition(): void {
    const id = Number(this.route.snapshot.paramMap.get('exhibitionId'));
    this.exhibitionService.getExhibition(id).subscribe(exhibition => this.exhibition = exhibition)
  }

  getExhibitionItem(): void {
    const id = Number(this.route.snapshot.paramMap.get('itemId'));
    this.exhibitionItemService.getExhibitionItem(id).subscribe(item => this.item = item)
  }

}
