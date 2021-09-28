import { Component, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';
import { Marker } from 'src/app/model/marker';
import { ExhibitionService } from 'src/app/service/exhibition.service';

import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import XYZ from 'ol/source/XYZ';


@Component({
  selector: 'app-map-form',
  templateUrl: './map-form.component.html',
  styleUrls: ['./map-form.component.css']
})




export class MapFormComponent implements OnInit {


  exhibition? : Exhibition

  title : string = ""

  coordinates : string = ""

  emptymarker = new Marker(0,"","")

  markers : Marker[] = [this.emptymarker]
  map: Map | undefined;


  constructor(
    private exhibitionService : ExhibitionService
  ) {
   }

  ngOnInit(): void {
    this.exhibition = this.exhibitionService.getSessionExhibition()

      this.map = new Map({
        target: 'map',
        layers: [
          new TileLayer({
            source: new XYZ({
              url: 'https://{a-c}.tile.openstreetmap.org/{z}/{x}/{y}.png'
            })
          })
        ],
        view: new View({
          center: [0, 0],
          zoom: 2
        })
      });
  }

  addMarker() {
    console.log("add marker button pressed")
  }

  removeMarker() {
    console.log("remove marker button pressed")
  }



 

}
