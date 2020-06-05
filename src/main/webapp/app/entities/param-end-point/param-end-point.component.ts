import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IParamEndPoint } from 'app/shared/model/param-end-point.model';
import { ParamEndPointService } from './param-end-point.service';
import { ParamEndPointDeleteDialogComponent } from './param-end-point-delete-dialog.component';

@Component({
  selector: 'jhi-param-end-point',
  templateUrl: './param-end-point.component.html'
})
export class ParamEndPointComponent implements OnInit, OnDestroy {
  paramEndPoints?: IParamEndPoint[];
  eventSubscriber?: Subscription;

  constructor(
    protected paramEndPointService: ParamEndPointService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.paramEndPointService.query().subscribe((res: HttpResponse<IParamEndPoint[]>) => (this.paramEndPoints = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInParamEndPoints();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IParamEndPoint): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInParamEndPoints(): void {
    this.eventSubscriber = this.eventManager.subscribe('paramEndPointListModification', () => this.loadAll());
  }

  delete(paramEndPoint: IParamEndPoint): void {
    const modalRef = this.modalService.open(ParamEndPointDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paramEndPoint = paramEndPoint;
  }
}
