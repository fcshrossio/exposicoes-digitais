import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Exhibition } from 'src/app/model/exhibition';
import { ExhibitionService } from 'src/app/service/exhibition.service';
import { ExhibitionCreationForm } from './exhibition-creation-model';

@Component({
  selector: 'app-exhibition-creation-form',
  templateUrl: './exhibition-creation-form.component.html',
  styleUrls: ['./exhibition-creation-form.component.css']
})
export class ExhibitionCreationFormComponent {


  model = new ExhibitionCreationForm('title','subtitulo','editor');

  submitted = false;

  onSubmit() {
     this.submitted = true; 
      //var newExhibition: Exhibition = new Exhibition(this.model.title,this.model.subtitle,this.model.editor)
    
      //this.exhibitionService.createExhibition(newExhibition)

      //this.router.navigate(['exhibition', newExhibition.id])
    }

    constructor(private exhibitionService: ExhibitionService,private router: Router) { }


}
