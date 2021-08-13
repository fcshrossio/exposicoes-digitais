import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ExhibitionListComponent } from './component/exhibition-list/exhibition-list.component';
import { ExhibitionDetailComponent } from './component/exhibition-detail/exhibition-detail.component';
import { HomepageComponent } from './component/homepage/homepage.component';
import { DashboardComponent } from './component/admin/dashboard/dashboard.component';
import { ExhibitionCreationFormComponent } from './component/exhibition-creation-form/exhibition-creation-form.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ExhibitionEditionComponent } from './component/exhibition-edition/exhibition-edition.component';
import { ExhibitionListHomepageComponent } from './component/exhibition-list-homepage/exhibition-list-homepage.component';
import { ItemDetailEditionComponent } from './component/item-detail-edition/item-detail-edition.component';
import { ItemListComponent } from './component/item-list/item-list.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginFormComponent } from './component/form/login-form/login-form.component';
import { AdminRoutingModule } from './component/admin/admin-routing.module';
import { AdminComponent } from './component/admin/admin.component';
import { HeaderAdminComponent } from './component/admin/header-admin/header-admin.component';
import { SidebarComponent } from './component/admin/sidebar/sidebar.component';
import { CreateExhibitionComponent } from './component/admin/create-exhibition/create-exhibition.component';
import { ExhibitionDraftsComponent } from './component/admin/exhibition-drafts/exhibition-drafts.component';
import { ExhibitionListAdminComponent } from './component/admin/exhibition-list-admin/exhibition-list-admin.component';
import { TitleFormComponent } from './component/admin/create-exhibition/title-form/title-form.component';
import { IntroductionFormComponent } from './component/admin/create-exhibition/introduction-form/introduction-form.component';
import { SectionFormComponent } from './component/admin/create-exhibition/section-form/section-form.component';
import { MapFormComponent } from './component/admin/create-exhibition/map-form/map-form.component';
import { SearchComponent } from './component/admin/search/search.component';
import { ResourcesComponent } from './component/admin/resources/resources.component';


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
    ItemListComponent,
    LoginFormComponent,
    AdminComponent,
    HeaderAdminComponent,
    SidebarComponent,
    CreateExhibitionComponent,
    ExhibitionDraftsComponent,
    ExhibitionListAdminComponent,
    TitleFormComponent,
    IntroductionFormComponent,
    SectionFormComponent,
    MapFormComponent,
    SearchComponent,
    ResourcesComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    AdminRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
