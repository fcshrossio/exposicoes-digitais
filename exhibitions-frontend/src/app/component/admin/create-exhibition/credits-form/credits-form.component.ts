import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { Exhibition } from 'src/app/model/exhibition';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-credits-form',
  templateUrl: './credits-form.component.html',
  styleUrls: ['./credits-form.component.css']
})
export class CreditsFormComponent implements OnInit {

  @Input() exhibition? : Exhibition 

  @Output() exhibitionChange:EventEmitter<Exhibition> = new EventEmitter<Exhibition>()

  // htmlContent =
  // '<p><span>Hel</span>looooo<span style="font-size:72.0pt;">oooo</span>oooooooooooooo</p><p><span style="font-weight:bold;">Hello</span></p>';

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

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit(){
    this.exhibitionChange.emit(this.exhibition)
  }

  preview(){
    //console.log("HTML: " + String(this.htmlContent))
  }
}
