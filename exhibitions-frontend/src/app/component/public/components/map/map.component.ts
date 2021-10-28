import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';


import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import XYZ from 'ol/source/XYZ';
import Vector from 'ol/source/Vector';
import { Feature } from 'ol';
import Geometry from 'ol/geom/Geometry';
import Source from 'ol/source/Source';
import Layer from 'ol/layer/Layer';
import Point from 'ol/geom/Point';
import VectorSource from 'ol/source/Vector';
import VectorLayer from 'ol/layer/Vector';

import { fromLonLat } from 'ol/proj';
import {Icon, Style} from 'ol/style';
import { Exhibition } from 'src/app/model/exhibition';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  map: Map | undefined;

  @Input() exhibition? : Exhibition 

  @Output() exhibitionChange:EventEmitter<Exhibition> = new EventEmitter<Exhibition>()
  

  markers: Feature<Geometry>[] = [];
  vectorSource: VectorSource<Geometry> | undefined
  vectorLayer: VectorLayer<any> | undefined

  iconStyle = new Style({
    image: new Icon({
      anchor: [0, 0],
      anchorXUnits: 'fraction',
      anchorYUnits: 'pixels',
      src: "https://www.fillmurray.com/30/30",
    }),
  });

  constructor() { }

  ngOnInit(): void {

    if(this.exhibition)
    {
      this.exhibition.markers.forEach(
        element => {
            var newMarker = new Feature({
              geometry: new Point(fromLonLat(element.coordinates)),
              name : element.title
            })
            //newMarker.setStyle(this.iconStyle)
            this.markers.push(newMarker)
       }
      )
    }
    

    this.vectorSource = new VectorSource({
      features: this.markers
    });

    this.vectorLayer = new VectorLayer({
      source: this.vectorSource
    })

    this.map = new Map({
      target: 'map',
      layers: [
        new TileLayer({
          source: new XYZ({
            url: 'https://{a-c}.tile.openstreetmap.org/{z}/{x}/{y}.png',
          })
        }),
        this.vectorLayer
      ],
      view: new View({
        center: fromLonLat([ -9.138574381126347, 38.72219608332645,]),
        zoom: 5
      })
    });
  }

}
