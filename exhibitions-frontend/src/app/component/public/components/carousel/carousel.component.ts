import { Component, Input, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
})
export class CarouselComponent implements OnInit {

  images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);

  @Input() exhibitions? : Exhibition[]

  constructor() { }

  ngOnInit(): void {
  }

}
