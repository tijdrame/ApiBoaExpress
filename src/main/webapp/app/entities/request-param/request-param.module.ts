import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ApiBoaExpressSharedModule } from 'app/shared/shared.module';
import { RequestParamComponent } from './request-param.component';
import { RequestParamDetailComponent } from './request-param-detail.component';
import { RequestParamUpdateComponent } from './request-param-update.component';
import { RequestParamDeleteDialogComponent } from './request-param-delete-dialog.component';
import { requestParamRoute } from './request-param.route';

@NgModule({
  imports: [ApiBoaExpressSharedModule, RouterModule.forChild(requestParamRoute)],
  declarations: [RequestParamComponent, RequestParamDetailComponent, RequestParamUpdateComponent, RequestParamDeleteDialogComponent],
  entryComponents: [RequestParamDeleteDialogComponent]
})
export class ApiBoaExpressRequestParamModule {}
