import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';
import { isTemplateSpan } from 'typescript';
import { ResourceShowcaseModalComponent } from '../../modals/resource-showcase-modal/resource-showcase-modal.component';

@Component({
  selector: 'app-exhibition-showcase',
  templateUrl: './exhibition-showcase.component.html',
  styleUrls: ['./exhibition-showcase.component.css']
})
export class ExhibitionShowcaseComponent implements OnInit {

  exhibition? : Exhibition

  exhibitions: Exhibition[] = []

  selectedSection: number = 0

  constructor(
    private route: ActivatedRoute,
    private exhibitionService: ExhibitionService,
    private modalService: NgbModal
    ) { }

  ngOnInit(): void {
    this.getExhibition()
    this.getExhibitions()
  }

  getExhibition(): void 
  {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.exhibitionService.getExhibition(id).subscribe( exhibition => this.exhibition = exhibition)
  }

  getExhibitions(): void 
  {
    this.exhibitionService.getExhibitions().subscribe( exhibitions => this.exhibitions = exhibitions)
  }

  changeSection(selection:number): void
  {
    if(this.exhibition && selection < this.exhibition?.items.length)
    this.selectedSection = selection
    console.log(this.selectedSection)
  }

  open() {
    //this.modalService.open(content, { size: 'xl', backdrop: 'static' })
    const modalRef = this.modalService.open(ResourceShowcaseModalComponent,{ size: 'xl', backdrop: 'static' });
    modalRef.componentInstance.digitalResource = this.exhibition?.cover;
  }

}
