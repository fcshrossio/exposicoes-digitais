import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PublicComponent } from './public.component';
import { HomepageComponent } from './homepage/homepage.component';
import { ExhibitionPageComponent } from './exhibition-page/exhibition-page.component';
import { ExhibitionShowcaseComponent } from './exhibition-showcase/exhibition-showcase.component';


const routes: Routes = [
     { path: '', component: PublicComponent,
        children: [
          { path: 'homepage', component: HomepageComponent },
          { path: 'exhibition', component: ExhibitionPageComponent },
          { path: 'exhibition/:id', component: ExhibitionShowcaseComponent }
        ] },
]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class PublicRoutingModule { }
