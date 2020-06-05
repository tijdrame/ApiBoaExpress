import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ApiBoaExpressSharedModule } from 'app/shared/shared.module';
import { TrackingComponent } from './tracking.component';
import { TrackingDetailComponent } from './tracking-detail.component';
import { TrackingUpdateComponent } from './tracking-update.component';
import { TrackingDeleteDialogComponent } from './tracking-delete-dialog.component';
import { trackingRoute } from './tracking.route';

@NgModule({
  imports: [ApiBoaExpressSharedModule, RouterModule.forChild(trackingRoute)],
  declarations: [TrackingComponent, TrackingDetailComponent, TrackingUpdateComponent, TrackingDeleteDialogComponent],
  entryComponents: [TrackingDeleteDialogComponent]
})
export class ApiBoaExpressTrackingModule {}
