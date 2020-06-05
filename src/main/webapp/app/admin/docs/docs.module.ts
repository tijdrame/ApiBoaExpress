import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ApiBoaExpressSharedModule } from 'app/shared/shared.module';

import { DocsComponent } from './docs.component';

import { docsRoute } from './docs.route';

@NgModule({
  imports: [ApiBoaExpressSharedModule, RouterModule.forChild([docsRoute])],
  declarations: [DocsComponent]
})
export class DocsModule {}
