import { Component, Input, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';

@Component({
  selector: 'app-exhibition-card',
  templateUrl: './exhibition-card.component.html',
  styleUrls: ['./exhibition-card.component.css']
})
export class ExhibitionCardComponent implements OnInit {

  @Input() exhibition? : Exhibition

  constructor() { }

  ngOnInit(): void {
  }

}
