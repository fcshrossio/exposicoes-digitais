import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { DigitalResource } from 'src/app/model/digitalResource';

@Component({
  selector: 'app-resource-details-modal',
  templateUrl: './resource-details-modal.component.html',
  styleUrls: ['./resource-details-modal.component.css']
})
export class ResourceDetailsModalComponent implements OnInit {

  @Input() digitalResource : DigitalResource | undefined


  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

}
