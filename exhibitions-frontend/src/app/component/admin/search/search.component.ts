import { Component, OnInit } from '@angular/core';
import { DigitalResource } from 'src/app/model/digitalResource';
import { DigitalResourcesService } from 'src/app/service/digital-resources.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  digitalResources : DigitalResource[] = []

  viewType : string = 'images'

  search : string | undefined

  constructor(
    private digitalResourceService : DigitalResourcesService
  ) { }

  ngOnInit(): void {
  }

  getResources(){
    this.digitalResourceService.getResources().subscribe( resources => {
      this.digitalResources = resources
      //console.log(resources)
    }  )
  }

  changeView(type : string){
    this.viewType = type
  }

}
