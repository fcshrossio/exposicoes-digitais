import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { DigitalResource } from 'src/app/model/digitalResource';
import { DigitalResourcesService } from 'src/app/service/digital-resources.service';

@Component({
  selector: 'app-resource-list-modal',
  templateUrl: './resource-list-modal.component.html',
  styleUrls: ['./resource-list-modal.component.css']
})
export class ResourceListModalComponent implements OnInit {

  @Input() cover? : DigitalResource 

  @Output() coverChange:EventEmitter<DigitalResource> = new EventEmitter<DigitalResource>()

  selection : DigitalResource[] = []

  limit : number = 1

  digitalResources : DigitalResource[] = []

  viewType : string = 'images'

  getResources(){
    this.digitalResourceService.getResources().subscribe( resources => {
      this.digitalResources = resources
      console.log(resources)
    }  )
  }

  changeView(type : string){
    this.viewType = type
  }


  constructor(
    public activeModal: NgbActiveModal,
    private digitalResourceService : DigitalResourcesService) { }

  ngOnInit(): void {
    console.log(this.cover)
    this.getResources()
  }

  insert(): void {
    console.log("insert")
    if(this.selection.length > 0)
    {
      this.cover = this.selection[0]
      console.log(this.cover)
      this.coverChange.emit(this.cover)
    }
    
  }

  onCheckBoxChange(e:any){

      if(e.target.checked) {
        console.log("checked" +  typeof(Number(e.target.value)))
        var resource: DigitalResource | undefined = this.digitalResources.find( resource => resource.id == Number(e.target.value))
        if(resource)
        {
          this.selection.push(resource)
        }
        
      }
      
  }

}
