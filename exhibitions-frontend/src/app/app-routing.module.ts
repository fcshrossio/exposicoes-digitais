import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './component/admin/dashboard/dashboard.component';
import { ExhibitionDetailComponent } from './component/exhibition-detail/exhibition-detail.component';
import { HomepageComponent } from './component/homepage/homepage.component';

const routes: Routes = [

  { path: 'homepage', component: HomepageComponent },
  { path: '', redirectTo: '/homepage', pathMatch: 'full' },
  { path: 'exhibition', component: ExhibitionDetailComponent },
  { path: 'exhibition/:id', component: ExhibitionDetailComponent },
  // { path: 'dashboard/exhibition/:exhibitionId', component: ExhibitionEditionComponent },
  // { path: 'dashboard/exhibition/:exhibitionId/item/:itemId', component: ExhibitionEditionComponent },
  { path: 'dashboard', component: DashboardComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
