import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ApiBoaExpressSharedModule } from 'app/shared/shared.module';
import { PaysComponent } from './pays.component';
import { PaysDetailComponent } from './pays-detail.component';
import { paysRoute } from './pays.route';

@NgModule({
  imports: [ApiBoaExpressSharedModule, RouterModule.forChild(paysRoute)],
  declarations: [PaysComponent, PaysDetailComponent]
})
export class ApiBoaExpressPaysModule {}
