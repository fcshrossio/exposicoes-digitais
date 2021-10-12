import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AuxiliaryMaterials } from 'src/app/model/auxiliaryMaterials';
import { Exhibition } from 'src/app/model/exhibition';

@Component({
  selector: 'app-auxiliary-items-form',
  templateUrl: './auxiliary-items-form.component.html',
  styleUrls: ['./auxiliary-items-form.component.css']
})
export class AuxiliaryItemsFormComponent implements OnInit {

  @Input() exhibition? : Exhibition 

  @Output() exhibitionChange:EventEmitter<Exhibition> = new EventEmitter<Exhibition>()


  auxiliaryMaterials? : AuxiliaryMaterials = new AuxiliaryMaterials(0)
  htmlContent : any

  onlineResourcesNova : any

  bibliography : any

  audiovisualResources : any

  webPlaces : any

  constructor() { 
    if(this.exhibition)
    {
      this.auxiliaryMaterials = new AuxiliaryMaterials(this.exhibition.id)
    }
  }

  ngOnInit(): void {
  }

}
