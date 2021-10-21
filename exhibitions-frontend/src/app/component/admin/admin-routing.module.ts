import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateExhibitionComponent } from './create-exhibition/create-exhibition.component';
import { ExhibitionDraftsComponent } from './exhibition-drafts/exhibition-drafts.component';
import { ExhibitionListAdminComponent } from './exhibition-list-admin/exhibition-list-admin.component';
import { TitleFormComponent } from './create-exhibition/title-form/title-form.component';
import { IntroductionFormComponent } from './create-exhibition/introduction-form/introduction-form.component';
import { MapFormComponent } from './create-exhibition/map-form/map-form.component';
import { SectionFormComponent } from './create-exhibition/section-form/section-form.component';
import { SearchComponent } from './search/search.component';
import { ResourcesComponent } from './resources/resources.component';
import { AuxiliaryItemsFormComponent } from './create-exhibition/auxiliary-items-form/auxiliary-items-form.component';

const routes: Routes = [
  { path: 'admin', redirectTo: 'admin/dashboard', pathMatch: 'full' },
  { path: 'admin', component: AdminComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'search', component: SearchComponent },
      { path: 'resources', component: ResourcesComponent },
      { path: 'exhibitions/create', redirectTo: 'exhibitions/create/title', pathMatch: 'full' },
      { path: 'exhibitions/create', component: CreateExhibitionComponent,
      children: [
        { path: 'title', component: TitleFormComponent },
        { path: 'introduction', component: IntroductionFormComponent },
        { path: 'sections' , component: SectionFormComponent },
        { path: 'map', component: MapFormComponent },
        { path: 'auxiliary-items', component: AuxiliaryItemsFormComponent}
     
      ]},
      { path: 'exhibitions/drafts', component: ExhibitionDraftsComponent },
      { path: 'exhibitions/edit/:id', component: CreateExhibitionComponent },
      { path: 'exhibitions', component: ExhibitionListAdminComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AdminRoutingModule { }
