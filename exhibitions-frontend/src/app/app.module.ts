import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ExhibitionListComponent } from './component/exhibition-list/exhibition-list.component';
import { ExhibitionDetailComponent } from './component/exhibition-detail/exhibition-detail.component';
import { HomepageComponent } from './component/homepage/homepage.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { ExhibitionCreationFormComponent } from './component/exhibition-creation-form/exhibition-creation-form.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ExhibitionEditionComponent } from './component/exhibition-edition/exhibition-edition.component';
import { ExhibitionListHomepageComponent } from './component/exhibition-list-homepage/exhibition-list-homepage.component';
import { ItemDetailEditionComponent } from './component/item-detail-edition/item-detail-edition.component';
import { ItemListComponent } from './component/item-list/item-list.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    ExhibitionListComponent,
    ExhibitionDetailComponent,
    HomepageComponent,
    DashboardComponent,
    ExhibitionCreationFormComponent,
    ExhibitionEditionComponent,
    ExhibitionListHomepageComponent,
    ItemDetailEditionComponent,
    ItemListComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
