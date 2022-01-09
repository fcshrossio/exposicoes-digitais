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

import { fromLonLat, toLonLat, transform } from 'ol/proj';
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

  emptymarker = new Marker(0,[0,0],"")

  mymarkers : Marker[] = [this.emptymarker]

  choosenSection = 0;

  markerLimit = 30;

  availableSections = new Array(this.markerLimit-1)

  map: Map | undefined;

  markers: Feature<Geometry>[] = [];
  vectorSource: VectorSource<Geometry> | undefined
  vectorLayer: VectorLayer<any> | undefined

  overlay: Overlay | undefined

  iconStyle = new Style({
    image: new Icon({
      anchor: [0.5,34],
      anchorXUnits: 'fraction',
      anchorYUnits: 'pixels',
      src: "../../../../../assets/icons/icon_map_point_spot.svg",
    }),
  });

  constructor(
    private exhibitionService : ExhibitionService
  ) {
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
 
    if(this.exhibition)
    {
      this.exhibition.markers.forEach(
        element => {
            var newMarker = new Feature({
              geometry: new Point(fromLonLat(element.coordinates)),
              name : element.title
            })
            newMarker.setStyle(this.iconStyle)
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
          zoom: 7
        })
      });

      if(this.overlay && content)
      {
        this.map.addOverlay(this.overlay)



        this.map.on('singleclick',  (evt: any) => {
            var lonLat = toLonLat(evt.coordinate);
            this.addMarker(lonLat[0], lonLat[1],this.choosenSection);
            console.log(this.vectorSource)
            if(this.exhibition)
            {
              this.exhibition.markers[this.choosenSection].coordinates = lonLat
            } 
          }
        );
        
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

  addMarker(lon: any , lat: any, markerIndex: number) {
    console.log("add marker")
    console.log(typeof(lon))
      
      var newMarker = new Feature({
        geometry: new Point(transform([lon,lat], 'EPSG:4326', 'EPSG:3857')),
        name : "Évora"
      })
      newMarker.setStyle(this.iconStyle)

      if(this.exhibition){
        this.markers[markerIndex] = newMarker

      this.vectorSource?.clear();

      this.vectorSource?.addFeatures(this.markers)
      }



    }

    
  
  addSection() {
     if(this.exhibition && this.exhibition.markers.length < this.markerLimit){
    // // this.exhibition.items.push(new ExhibitionItem(this.exhibition.items.length+1,0,this.exhibition.items.length+1,""))
    // // this.availableSections.pop()
     var newMarker: Marker = new Marker(0,[0.0,0.0],"")
     this.exhibitionService.addExhibitionMarker(this.exhibition.id,newMarker).subscribe(
       exhibitionMarker => { 
            this.exhibition?.markers.push(exhibitionMarker)
            this.availableSections.pop()
            console.log( "exhibition marker added: " + exhibitionMarker)
            if( this.exhibition){
              this.choosenSection = this.exhibition.items.length -1
             }
          }
        ) 
     }
  }

  removeMarker(id : number) {
    if(this.exhibition && this.exhibition.markers.length > 0)
    {
    console.log("remove marker button pressed id "+ id)

    this.exhibitionService.removeExhibitionMarker(this.exhibition.id, id ).subscribe(
      exhibition => {
        this.exhibition = exhibition
        console.log( "exhibition details updated: " + exhibition)
      }
    )
    }
  }

  
  changeSection(index : number) {
    console.log(this.choosenSection)
    this.choosenSection = index
  }



 

}
