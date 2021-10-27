import { Component, Input, OnInit } from '@angular/core';
import { Exhibition } from 'src/app/model/exhibition';
import { Marker } from 'src/app/model/marker';
import { ExhibitionService } from 'src/app/service/exhibition.service';

import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import XYZ from 'ol/source/XYZ';
import Vector from 'ol/source/Vector';
import { Feature, Overlay } from 'ol';
import Geometry from 'ol/geom/Geometry';
import Source from 'ol/source/Source';
import Layer from 'ol/layer/Layer';
import Point from 'ol/geom/Point';
import VectorSource from 'ol/source/Vector';
import VectorLayer from 'ol/layer/Vector';

import { fromLonLat, toLonLat } from 'ol/proj';
import {Icon, Style} from 'ol/style';
import { toStringHDMS } from 'ol/coordinate';


@Component({
  selector: 'app-map-form',
  templateUrl: './map-form.component.html',
  styleUrls: ['./map-form.component.css']
})




export class MapFormComponent implements OnInit {


  @Input() exhibition? : Exhibition

  title : string = ""

  coordinates : number[] = [] 

  emptymarker = new Marker(0,"","")

  mymarkers : Marker[] = [this.emptymarker]

  choosenSection = 0;

  map: Map | undefined;

  markers: Feature<Geometry>[] = [];
  vectorSource: VectorSource<Geometry> | undefined
  vectorLayer: VectorLayer<any> | undefined

  overlay: Overlay | undefined

  iconStyle = new Style({
    image: new Icon({
      anchor: [0, 0],
      anchorXUnits: 'fraction',
      anchorYUnits: 'pixels',
      src: "https://www.fillmurray.com/30/30",
    }),
  });

  constructor() {
   }

  ngOnInit(): void {

    const container = document.getElementById('popup');
    const content = document.getElementById('popup-content');
    const closer = document.getElementById('popup-closer');

    if(container)
    {
      this.overlay = new Overlay({
        element: container,
        autoPan: true,
        autoPanAnimation: {
          duration: 250
        }
      });
    }
 

    this.markers.push(new Feature({
      geometry: new Point(fromLonLat([ -9.138574381126347,38.72219608332645])),
      name : "Lisboa"
    }));

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

      if(this.overlay && content)
      {
        this.map.addOverlay(this.overlay)

        // this.map.on('singleclick',  (evt: any) => {
        //   const coordinate = evt.coordinate;
        //   const hdms = toStringHDMS(toLonLat(coordinate));
        //   if(this.overlay){
        //     content.innerHTML = '<p>Current coordinates are :</p><code>' + hdms +
        //     '</code>';
        //   this.overlay.setPosition(coordinate);
        //   }
  
        // });

        this.map.on('singleclick',  (evt: any) => {
           const coordinate = evt.coordinate;
           const hdms = toStringHDMS(toLonLat(coordinate));
           //console.log(toLonLat(coordinate))
           if(coordinate.length > 0)
           {
            console.log(toLonLat(coordinate))
            this.coordinates = coordinate
           }
          }
        );
        var hit = false

        this.map.on('click', (evt: any) => {
          if(this.map){
            const feature = this.map.forEachFeatureAtPixel(evt.pixel, function (feature) {
              hit = true
              return feature;
            });
            if (feature && true) {
              const coordinate = evt.coordinate;
              const hdms = toStringHDMS(toLonLat(coordinate));
              if(this.overlay){
                content.innerHTML = '<p>Current coordinates are :</p><code>' + hdms +
                '</code>';
              this.overlay.setPosition(coordinate);
              }
            }
          }
        });
      }


    
  }

  convertStringToCoordinates(coordinates: string) : number[] {
    var arrayOfString : number[];
    arrayOfString = []
    if(coordinates){
      arrayOfString = coordinates?.split(",").map(Number)
    }
    console.log(arrayOfString)
    console.log([-7.907558, 38.571703 ])
    return arrayOfString
  }

  addMarker() {
    console.log("add marker button pressed")
    if(this.coordinates && this.coordinates.length > 0)
    {
      
      var newMarker2 = new Feature({
        geometry: new Point(this.coordinates),
        name : "Évora"
      })

      newMarker2.setStyle(this.iconStyle)
      this.vectorSource?.addFeature(newMarker2)
    }
    
    // var newMarker = new Feature({
    //   geometry: new Point(fromLonLat([-7.907558, 38.571703 ])),
    //   name : "Évora"
    // })

    // newMarker.setStyle(this.iconStyle)

    // this.vectorSource?.addFeature(newMarker)
  }

  removeMarker() {
    console.log("remove marker button pressed")
  }

  
  changeSection(index : number) {
    console.log(this.choosenSection)
    this.choosenSection = index
  }



 

}
