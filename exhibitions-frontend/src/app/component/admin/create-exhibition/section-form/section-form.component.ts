import { Component, Input, OnInit } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionItem } from 'src/app/model/exhibitionItem';
import { ExhibitionSubItem } from 'src/app/model/exhibitionSubItem';
import { ExhibitionService } from 'src/app/service/exhibition.service';


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
  ) { }

  ngOnInit(): void {
    if(this.exhibition)
    {
      this.availableSections = new Array(this.sectionLimit-1-this.exhibition.items[this.choosenSection].subItems.length)
    }
   
  }

  changeSection(id : number) {
    console.log(this.choosenSection)
    this.choosenSection = id-1
  }

  addSection() {
    if(this.exhibition && this.exhibition.items.length < this.sectionLimit)
    this.exhibition.items.push(new ExhibitionItem(this.exhibition.items.length+1,0,this.exhibition.items.length+1,""))
    this.availableSections.pop()
  }

  addSubSection(type : string){
    if(this.exhibition)
    {
        var subsection = new ExhibitionSubItem(0,this.exhibition.id,0,this.exhibition.items[this.choosenSection].subItems.length,type)
        this.exhibition.items[this.choosenSection].subItems.push( subsection)
        
    }
  }

}
