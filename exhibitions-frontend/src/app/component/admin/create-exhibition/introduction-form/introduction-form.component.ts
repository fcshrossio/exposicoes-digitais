import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionItem} from 'src/app/model/exhibitionItem'
import { ExhibitionService } from 'src/app/service/exhibition.service';
import { ResourceListModalComponent } from '../../modals/resource-list-modal/resource-list-modal.component';

@Component({
  selector: 'app-introduction-form',
  templateUrl: './introduction-form.component.html',
  styleUrls: ['./introduction-form.component.css']
})
export class IntroductionFormComponent implements OnInit {


  editableName: string = "FCG/BA - Teatro de São Carlos"

  file: any

  keywords: string[] = ["Teste1", "Teste2"]


  @Input() exhibition? : Exhibition 

  @Output() exhibitionChange:EventEmitter<Exhibition> = new EventEmitter<Exhibition>()


  constructor(
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
  }

  
  onSubmit(){
    this.exhibitionChange.emit(this.exhibition)
  }


  onFileDropped($event : any) {
    //console.log("on file dropped")
    for (const item of $event) {
      this.file = item;
    }
    console.log(this.file)
    if(this.file.name){
      this.editableName = this.file.name
    }
}

  removeKeyword( keywordRemove : string) {
    if(this.exhibition)
    {
      this.exhibition.keywords = this.exhibition.keywords.filter((keyword) => keywordRemove != keyword)
    }
  }

  onChangeKeyword(e : any){
    if(this.exhibition)
    {
      console.log(e.target.value)
      var keyword : string = e.target.value 
      if(this.keywords.includes(e.target.value))
      {
        this.exhibition.keywords.push(keyword) 
      } 
    }
  }

  open() {
    if(this.exhibition){
    console.log("clicked open")
    const modalRef = this.modalService.open(ResourceListModalComponent,{ size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cover = this.exhibition?.cover;
    modalRef.componentInstance.coverChange.subscribe((cover: any) => { 
      if(this.exhibition){
      this.exhibition.cover = cover
      }})
    }
  }

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



}
