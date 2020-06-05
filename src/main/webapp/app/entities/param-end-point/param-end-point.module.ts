import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ApiBoaExpressSharedModule } from 'app/shared/shared.module';
import { ParamEndPointComponent } from './param-end-point.component';
import { ParamEndPointDetailComponent } from './param-end-point-detail.component';
import { ParamEndPointUpdateComponent } from './param-end-point-update.component';
import { ParamEndPointDeleteDialogComponent } from './param-end-point-delete-dialog.component';
import { paramEndPointRoute } from './param-end-point.route';

@NgModule({
  imports: [ApiBoaExpressSharedModule, RouterModule.forChild(paramEndPointRoute)],
  declarations: [ParamEndPointComponent, ParamEndPointDetailComponent, ParamEndPointUpdateComponent, ParamEndPointDeleteDialogComponent],
  entryComponents: [ParamEndPointDeleteDialogComponent]
})
export class ApiBoaExpressParamEndPointModule {}
