import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './component/public/pages/homepage/homepage.component';
import { DashboardComponent } from './component/admin/dashboard/dashboard.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
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
import { AuxiliaryItemsFormComponent } from './component/admin/create-exhibition/auxiliary-items-form/auxiliary-items-form.component';
import { DraganddropDirective } from './component/admin/create-exhibition/introduction-form/draganddrop.directive';
import { DraganddroplistDirective } from './component/admin/create-exhibition/section-form/draganddroplist.directive';

import { AngularEditorModule } from '@kolkov/angular-editor';
import { PublicRoutingModule } from './component/public/public-routing.module';
import { PublicComponent } from './component/public/public.component';
import { HeaderComponent } from './component/public/header/header.component';
import { FooterComponent } from './component/public/footer/footer.component';
import { ExhibitionPageComponent } from './component/public/pages/exhibition-page/exhibition-page.component';
import { ExhibitionShowcaseComponent } from './component/public/pages/exhibition-showcase/exhibition-showcase.component';
import { CarouselComponent } from './component/public/components/carousel/carousel.component';
import { ExhibitionCardComponent } from './component/public/components/exhibition-card/exhibition-card.component';
import { CreditsFormComponent } from './component/admin/create-exhibition/credits-form/credits-form.component';
import { ResourceDetailsModalComponent } from './component/public/modals/resource-details-modal/resource-details-modal.component';
import { ResourceShowcaseModalComponent } from './component/public/modals/resource-showcase-modal/resource-showcase-modal.component';
import { MapComponent } from './component/public/components/map/map.component';
import { ResourceListModalComponent } from './component/admin/modals/resource-list-modal/resource-list-modal.component';


@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    DashboardComponent,
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
    ResourcesComponent,
    AuxiliaryItemsFormComponent,
    DraganddropDirective,
    DraganddroplistDirective,
    PublicComponent,
    HeaderComponent,
    FooterComponent,
    ExhibitionPageComponent,
    ExhibitionShowcaseComponent,
    CarouselComponent,
    ExhibitionCardComponent,
    CreditsFormComponent,
    ResourceDetailsModalComponent,
    ResourceShowcaseModalComponent,
    MapComponent,
    ResourceListModalComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    AdminRoutingModule,
    AngularEditorModule,
    PublicRoutingModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
