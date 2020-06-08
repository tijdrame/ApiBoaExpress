import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ChartsModule } from 'ng2-charts';


import './vendor';
import { ApiBoaExpressSharedModule } from 'app/shared/shared.module';
import { ApiBoaExpressCoreModule } from 'app/core/core.module';
import { ApiBoaExpressAppRoutingModule } from './app-routing.module';
import { ApiBoaExpressHomeModule } from './home/home.module';
import { ApiBoaExpressEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    ChartsModule,
    ApiBoaExpressSharedModule,
    ApiBoaExpressCoreModule,
    ApiBoaExpressHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    ApiBoaExpressEntityModule,
    ApiBoaExpressAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent]
})
export class ApiBoaExpressAppModule {}
