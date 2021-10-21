import { Component, OnInit } from '@angular/core';
import { DigitalResource } from 'src/app/model/digitalResource';
import { DigitalResourcesService } from 'src/app/service/digital-resources.service';

@Component({
  selector: 'app-resources',
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.css']
})
export class ResourcesComponent implements OnInit {

  digitalResources : DigitalResource[] = []

  viewType : string = 'images'

  constructor(
    private digitalResourceService : DigitalResourcesService
  ) { }

  ngOnInit(): void {
    this.getResources()
  }

  getResources(){
    this.digitalResourceService.getResources().subscribe( resources => {
      this.digitalResources = resources
      console.log(resources)
    }  )
  }

  changeView(type : string){
    this.viewType = type
  }

}
