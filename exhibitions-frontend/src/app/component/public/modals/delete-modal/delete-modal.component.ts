import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ExhibitionService } from 'src/app/service/exhibition.service';

@Component({
  selector: 'app-delete-modal',
  templateUrl: './delete-modal.component.html',
  styleUrls: ['./delete-modal.component.css']
})
export class DeleteModalComponent implements OnInit {

  @Input() exhibitionId : number | undefined

  constructor(
    public activeModal: NgbActiveModal,
    private exhibitionService: ExhibitionService
    ) { }

  ngOnInit(): void {
  }


  deleteExhibition(){
    if(this.exhibitionId)
    {
      this.exhibitionService.deleteExhibition(this.exhibitionId).subscribe(
        data => console.log(data)
      )

      this.activeModal.close()
    }
  }

}
