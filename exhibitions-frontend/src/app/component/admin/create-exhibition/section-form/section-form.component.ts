import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DragulaService } from 'ng2-dragula';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionItem } from 'src/app/model/exhibitionItem';
import { ExhibitionSubItem } from 'src/app/model/exhibitionSubItem';
import { ExhibitionItemService } from 'src/app/service/exhibition-item.service';
import { ExhibitionService } from 'src/app/service/exhibition.service';
import { ResourceListModalComponent } from '../../modals/resource-list-modal/resource-list-modal.component';


@Component({
  selector: 'app-section-form',
  templateUrl: './section-form.component.html',
  styleUrls: ['./section-form.component.css']
})
export class SectionFormComponent implements OnInit {

  sectionLimit = 30;

  choosenSection = 0;

  availableSections = new Array(this.sectionLimit-1)
 
  @Input() exhibition? : Exhibition

  exhibitionItems : ExhibitionItem[] = []

  @Output() exhibitionChange:EventEmitter<Exhibition> = new EventEmitter<Exhibition>()

  editorConfig: AngularEditorConfig = {
        editable: true,
          spellcheck: true,
          height: 'auto',
          minHeight: '200px',
          maxHeight: 'auto',
          width: 'auto',
          minWidth: '0',
          translate: 'yes',
          enableToolbar: true,
          showToolbar: true,
          defaultParagraphSeparator: '',
          defaultFontName: '',
          defaultFontSize: '',
          fonts: [
            {class: 'arial', name: 'Arial'},
            {class: 'times-new-roman', name: 'Times New Roman'},
            {class: 'calibri', name: 'Calibri'},
            {class: 'comic-sans-ms', name: 'Comic Sans MS'}
          ],
          customClasses: [
        
        ],
        sanitize: true,
        toolbarPosition: 'top',
        toolbarHiddenButtons: [
      [
        'subscript',
        'superscript',
        'justifyLeft',
        'justifyCenter',
        'justifyRight',
        'justifyFull',
        'indent',
        'outdent',
        'insertUnorderedList',
        'insertOrderedList',
        'heading',
        'fontName'
      ],
      [
        'textColor',
        'backgroundColor',
        'customClasses',
        'insertImage',
        'insertVideo',
        'insertHorizontalRule',
        'removeFormat',
        'toggleEditorMode'
      ]
    ]
    };

  htmlContent : any

  constructor(
    private exhibitionService : ExhibitionService,
    private exhibitionItemService : ExhibitionItemService,
    private dragulaService: DragulaService,
    private modalService: NgbModal
  ) {

 
   }

  ngOnInit(): void {
    console.log(this.exhibition)
    if(this.exhibition)
    {
      this.dragulaService.createGroup("ITEMS", {
        // ...
      });
  
      this.dragulaService.dropModel("ITEMS").subscribe(args => {
        console.log(args);
        //if(this.exhibition)
          //console.log(this.exhibition.items)
      });
  
      this.dragulaService.createGroup("SUBITEMS", {
      //   moves: function (el, container, handle) {
      //     return handle.classList.contains('handle');
      // }
      });
  
      this.dragulaService.dropModel("SUBITEMS").subscribe(args => {
        console.log(args);
        //if(this.exhibition)
          //console.log(this.exhibition.items)
      });
    




      this.exhibition.items = this.exhibition.items.sort((a, b) => {
        return a.position - b.position;
      });
      this.availableSections = new Array(this.sectionLimit-1-this.exhibition.items[this.choosenSection].subItems.length)
    }
   
  }

  

  

  changeSection(index : number) {
    console.log(this.choosenSection)
    this.choosenSection = index
  }

  saveOrder(){
    console.log(this.exhibition?.items)
    if(this.exhibition){
      this.exhibition.items.forEach( (item,index) => {
        item.position = index+1
      })
      console.log(this.exhibition?.items)
    }

  }

  saveSubOrder(){
    if(this.exhibition){
      this.exhibition.items[this.choosenSection].subItems.forEach( (subitem,index) => {
        subitem.position = index+1
      })
      console.log(this.exhibition.items[this.choosenSection].subItems)
    }

  }

  addSection() {
    if(this.exhibition && this.exhibition.items.length < this.sectionLimit){
    // this.exhibition.items.push(new ExhibitionItem(this.exhibition.items.length+1,0,this.exhibition.items.length+1,""))
    // this.availableSections.pop()
    var newItem: ExhibitionItem = new ExhibitionItem(0,0,this.exhibition.items.length+1,"")
    this.exhibitionService.addExhibitionItem(this.exhibition.id,newItem).subscribe(
      exhibition => { 
           this.exhibition = exhibition
           console.log( "exhibition details updated: " + exhibition)
         }
       ) 
    }
  }

  addSubSection(type : string){
    if(this.exhibition)
    {
        var item = this.exhibition.items[this.choosenSection]
        var subsection = new ExhibitionSubItem(0,item.subItems.length,type)
        this.exhibitionItemService.addExhibitionSubItem(item.id,subsection).subscribe(
          exhibitionItem => { 
              if(this.exhibition)
              {
                this.exhibition.items[this.choosenSection] = exhibitionItem
                console.log( "exhibition sub item added " + exhibitionItem)
              }
 
             }
           ) 
        //this.exhibition.items[this.choosenSection].subItems.push( subsection)
        
    }
  }

  removeSubSection(subsectionId : number){
      if(this.exhibition)
      {
      console.log("removing subsection : " + subsectionId)
      this.exhibitionItemService.removeExhibitionSubItem(this.exhibition.items[this.choosenSection].id,subsectionId).subscribe(
        exhibitionItem => { 
            if(this.exhibition)
            {
              this.exhibition.items[this.choosenSection] = exhibitionItem
              console.log( "exhibition sub item removed " + exhibitionItem)
            }

           }
         ) 
      }
  }

  open(subsectionId : number, index : number) {
    if(this.exhibition){
    console.log("clicked open")
    const modalRef = this.modalService.open(ResourceListModalComponent,{ size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cover = this.exhibition?.items[this.choosenSection].subItems
    modalRef.componentInstance.coverChange.subscribe((cover: any) => { 
      if(this.exhibition){
        var subsectionIndex = this.exhibition.items[this.choosenSection].subItems.findIndex( element => element.id == subsectionId)
        this.exhibition.items[this.choosenSection].subItems[subsectionIndex].digitalResources[index] = cover
      }})
    }
  }

}

