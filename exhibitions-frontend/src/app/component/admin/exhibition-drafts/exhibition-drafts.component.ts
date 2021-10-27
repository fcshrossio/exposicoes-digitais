import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';
import { DeleteModalComponent } from '../../public/modals/delete-modal/delete-modal.component';

@Component({
  selector: 'app-exhibition-drafts',
  templateUrl: './exhibition-drafts.component.html',
  styleUrls: ['./exhibition-drafts.component.css']
})
export class ExhibitionDraftsComponent implements OnInit {

  exhibitions: Exhibition[] = []

  selection: string = "all"

  collaborations: Exhibition[] = []

  drafts: Exhibition[] = []

  constructor(
    private exhibitionService: ExhibitionService,
    private modalService: NgbModal
    ) { }

  ngOnInit(): void {
    this.getExhibitions()

    //console.log(this.drafts)
  }


  getExhibitions(): void 
  {
    this.exhibitionService.getExhibitions().subscribe( exhibitions => { 
      this.exhibitions = exhibitions.filter( (element) => { return element.status != "PUBLISHED" })
      this.exhibitions = exhibitions.filter( (element) => { return element.status != "FORAPPROVAL" })
      this.exhibitions = exhibitions.filter( (element) => { return element.status === "DRAFT" })
      this.drafts = this.exhibitions.filter( (element) => { return element.editor.username === "Marco" })
      this.collaborations = this.exhibitions.filter( (element) => { return element.collaborators.length >= 1 })
    })
  }

  
  changeSelection( selection : string){
    this.selection = selection
  }

  open(exhibitionId : number) {
    //this.modalService.open(content, { size: 'xl', backdrop: 'static' })
    const modalRef = this.modalService.open(DeleteModalComponent);
    modalRef.componentInstance.exhibitionId =  exhibitionId
  }


}
