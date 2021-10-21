import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DigitalResource } from 'src/app/model/digitalResource';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionItem } from 'src/app/model/exhibitionItem';
import { ExhibitionSubItem } from 'src/app/model/exhibitionSubItem';
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

  id : number | undefined

  exhibitions: Exhibition[] = []

  exhibitionItems : ExhibitionItem[] = []

  exhibitionSubItems : ExhibitionSubItem[] = []

  digitalResources : DigitalResource[] = []

  selectedSection: number = 0

  selectedContent: number = 0

  selectedAuxiliaryMaterial : number = 0

  constructor(
    private route: ActivatedRoute,
    private exhibitionService: ExhibitionService,
    private modalService: NgbModal
    ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => { 
      this.id = params['id']; 
      if(this.id){
        this.exhibitionService.getExhibition(this.id).subscribe( exhibition => 
          {
            console.log(exhibition) 
            this.exhibition = exhibition
            this.exhibitionItems = exhibition.items
            this.selectedSection = 0
            this.selectedContent = 0
            this.exhibitionSubItems = this.exhibition?.items[this.selectedSection].subItems
            this.exhibitionSubItems.forEach(element => {
              this.digitalResources = this.digitalResources.concat(element.digitalResources)

            });
          }
        )
      }
    }
   )
    this.getExhibition()
    this.getExhibitions()
  }

  getExhibition(): void 
  {
    //const id = Number(this.route.snapshot.paramMap.get('id'));
 
    
    // })
  }

  getExhibitions(): void 
  {
    this.exhibitionService.getExhibitions().subscribe( exhibitions => this.exhibitions = exhibitions)
  }

  changeSection(selection:number): void
  {
    if(this.exhibition && selection < this.exhibition?.items.length)
    {
      this.selectedSection = selection
      this.exhibitionSubItems = this.exhibition?.items[this.selectedSection].subItems
    }
 
    console.log(this.selectedSection)
  }

  changeSelectedContent(selected: number) {
    console.log(selected)
    this.selectedContent = selected
  }

  changeSelectedAuxiliaryMaterial(selected: number) {
    console.log(selected)
    this.selectedAuxiliaryMaterial = selected
  }

  open() {
    console.log(this.digitalResources)
    //this.modalService.open(content, { size: 'xl', backdrop: 'static' })
    const modalRef = this.modalService.open(ResourceShowcaseModalComponent,{ size: 'lg' });
    modalRef.componentInstance.digitalResources = this.digitalResources;
  }

}
