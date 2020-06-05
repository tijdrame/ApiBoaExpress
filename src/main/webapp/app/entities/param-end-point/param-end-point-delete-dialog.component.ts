import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParamEndPoint } from 'app/shared/model/param-end-point.model';
import { ParamEndPointService } from './param-end-point.service';

@Component({
  templateUrl: './param-end-point-delete-dialog.component.html'
})
export class ParamEndPointDeleteDialogComponent {
  paramEndPoint?: IParamEndPoint;

  constructor(
    protected paramEndPointService: ParamEndPointService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paramEndPointService.delete(id).subscribe(() => {
      this.eventManager.broadcast('paramEndPointListModification');
      this.activeModal.close();
    });
  }
}
