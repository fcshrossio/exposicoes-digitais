import { Component, OnInit } from '@angular/core';
import { DigitalResource } from 'src/app/model/digitalResource';
import { SavedResources } from 'src/app/model/savedResources';
import { DigitalResourcesService } from 'src/app/service/digital-resources.service';
import { SavedResourcesService } from 'src/app/service/saved-resources.service';

@Component({
  selector: 'app-resources',
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.css']
})
export class ResourcesComponent implements OnInit {

  digitalResources : DigitalResource[] = []

  savedResources : SavedResources[] = []

  viewType : string = 'images'

  constructor(
    private digitalResourceService : DigitalResourcesService,
    private savedResourcesService : SavedResourcesService
  ) { }

  ngOnInit(): void {
    //this.getResources()
    this.getSavedResources()
  }
  getSavedResources() {
    this.savedResourcesService.getSavedResourcesLists().subscribe( resources => {
      this.savedResources = resources
    })
  }

  onChangeList(e : any){

    console.log(e.target.value)
    var listId : number = e.target.value 
    var selected: SavedResources | undefined = this.savedResources.find( element => element.id == listId)
    if(selected)
    this.digitalResources = selected.digitalResources
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
