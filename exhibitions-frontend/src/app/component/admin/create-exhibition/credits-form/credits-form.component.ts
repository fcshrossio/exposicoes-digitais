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

  htmlContent =
  '<p><span>Hel</span>looooo<span style="font-size:72.0pt;">oooo</span>oooooooooooooo</p><p><span style="font-weight:bold;">Hello</span></p>';

config: AngularEditorConfig = {
  editable: true,
  spellcheck: true,
  height: "15rem",
  minHeight: "5rem",
  placeholder: "Enter text here...",
  translate: "no",
  defaultParagraphSeparator: "p",
  defaultFontName: "Arial",
  toolbarHiddenButtons: [["bold"]],
  sanitize: false,
  customClasses: [
    {
      name: "quote",
      class: "quote"
    },
    {
      name: "redText",
      class: "redText"
    },
    {
      name: "titleText",
      class: "titleText",
      tag: "h1"
    }
  ]
};

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit(){
    this.exhibitionChange.emit(this.exhibition)
  }

  preview(){
    console.log("HTML: " + String(this.htmlContent))
  }
}
