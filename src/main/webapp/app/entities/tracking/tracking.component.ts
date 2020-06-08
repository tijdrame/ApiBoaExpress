import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITracking } from 'app/shared/model/tracking.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { TrackingService } from './tracking.service';
import { TrackingDeleteDialogComponent } from './tracking-delete-dialog.component';
import { isUndefined } from 'util';

@Component({
  selector: 'jhi-tracking',
  templateUrl: './tracking.component.html'
})
export class TrackingComponent implements OnInit, OnDestroy {
  trackings?: ITracking[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  dateDeb = '';
  dateFin = '';
  requestId = '';

  constructor(
    protected trackingService: TrackingService,
    protected activatedRoute: ActivatedRoute,
    protected dataUtils: JhiDataUtils,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.trackingService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ITracking[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInTrackings();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITracking): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInTrackings(): void {
    this.eventSubscriber = this.eventManager.subscribe('trackingListModification', () => this.loadPage());
  }

  delete(tracking: ITracking): void {
    const modalRef = this.modalService.open(TrackingDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tracking = tracking;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ITracking[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/tracking'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.trackings = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }

  search(page?: number): void {
    const pageToLoad: number = page || this.page;
    if(this.requestId===""||isUndefined(this.requestId))this.requestId=" ";
    this.trackingService
    .queryBis({dateDeb: this.dateDeb, dateFin : this.dateFin, 
      requestId: this.requestId,
      page: pageToLoad - 1,
      size: this.itemsPerPage,
      sort: this.sort()})
    .subscribe(
      (res: HttpResponse<ITracking[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
      () => this.onError()
    );
    this.requestId = this.requestId.trim();
  }
}
