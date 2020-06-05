import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRequestParam } from 'app/shared/model/request-param.model';
import { RequestParamService } from './request-param.service';

@Component({
  templateUrl: './request-param-delete-dialog.component.html'
})
export class RequestParamDeleteDialogComponent {
  requestParam?: IRequestParam;

  constructor(
    protected requestParamService: RequestParamService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.requestParamService.delete(id).subscribe(() => {
      this.eventManager.broadcast('requestParamListModification');
      this.activeModal.close();
    });
  }
}
