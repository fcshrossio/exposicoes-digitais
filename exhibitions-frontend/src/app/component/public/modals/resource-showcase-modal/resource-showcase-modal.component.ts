import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DigitalResource } from 'src/app/model/digitalResource';
import { ResourceDetailsModalComponent } from '../resource-details-modal/resource-details-modal.component';

@Component({
  selector: 'app-resource-showcase-modal',
  templateUrl: './resource-showcase-modal.component.html',
  styleUrls: ['./resource-showcase-modal.component.css']
})
export class ResourceShowcaseModalComponent implements OnInit {

  index : number = 0

  images = ["https://www.fillmurray.com/30/30","https://www.fillmurray.com/130/30","https://www.fillmurray.com/230/430" ]

  @Input() digitalResources : DigitalResource[] = []

  constructor(public activeModal: NgbActiveModal,private modalService: NgbModal) { }

  ngOnInit(): void {
    console.log(this.digitalResources)
  }

  changeSelect(selected: number){
    this.index++
  }

  open() {
    //this.modalService.open(content, { size: 'xl', backdrop: 'static' })
    const modalRef = this.modalService.open(ResourceDetailsModalComponent,{ size: 'xl', backdrop: 'static' });
    modalRef.componentInstance.digitalResource = new DigitalResource(0,"titulo","descrição","data provider", "","",[],"",[],"","https://www.fillmurray.com/30/30")
  }

}
