import { Component, Input, OnInit } from '@angular/core';
import { mockExhibitionItems } from 'src/app/mock-exhibitions';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionItem } from 'src/app/model/exhibitionItem';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  @Input() exhibition?: Exhibition;

  items: ExhibitionItem[] = mockExhibitionItems
 

  constructor() { }

  ngOnInit(): void {

  }


}
