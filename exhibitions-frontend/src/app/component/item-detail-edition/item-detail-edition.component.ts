import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ExhibitionItem } from 'src/app/model/exhibitionItem';

@Component({
  selector: 'app-item-detail-edition',
  templateUrl: './item-detail-edition.component.html',
  styleUrls: ['./item-detail-edition.component.css']
})
export class ItemDetailEditionComponent implements OnInit {


  @Input() item?: ExhibitionItem;

  

  constructor(
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    console.log("item-detail-edition")
    this.getExhibitionItem
  }

  getExhibitionItem(): void {
    const id = Number(this.route.snapshot.paramMap.get('itemId'));
    console.log("fetched item")
    console.log(id)
    //this.exhibitionItemService.getExhibitionItem(id).subscribe(item => this.item = item)
  }

}
