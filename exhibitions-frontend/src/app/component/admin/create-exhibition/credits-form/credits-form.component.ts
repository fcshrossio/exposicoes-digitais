import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';

@Component({
  selector: 'app-credits-form',
  templateUrl: './credits-form.component.html',
  styleUrls: ['./credits-form.component.css']
})
export class CreditsFormComponent implements OnInit {

  @Input() exhibition? : Exhibition 

  @Output() exhibitionChange:EventEmitter<Exhibition> = new EventEmitter<Exhibition>()

  constructor() { }

  ngOnInit(): void {
  }

}
