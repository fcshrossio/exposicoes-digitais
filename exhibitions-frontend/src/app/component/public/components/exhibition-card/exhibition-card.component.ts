import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Exhibition } from 'src/app/model/exhibition';
import { ResourceDetailsModalComponent } from '../../modals/resource-details-modal/resource-details-modal.component';

@Component({
  selector: 'app-exhibition-card',
  templateUrl: './exhibition-card.component.html',
  styleUrls: ['./exhibition-card.component.css']
})
export class ExhibitionCardComponent implements OnInit {

  @Input() exhibition? : Exhibition

  closeResult = '';

  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  open() {
    //this.modalService.open(content, { size: 'xl', backdrop: 'static' })
    const modalRef = this.modalService.open(ResourceDetailsModalComponent,{ size: 'xl', backdrop: 'static' });
    modalRef.componentInstance.digitalResource = this.exhibition?.cover;
  }


}
