import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ITracking, Tracking } from 'app/shared/model/tracking.model';
import { TrackingService } from './tracking.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-tracking-update',
  templateUrl: './tracking-update.component.html'
})
export class TrackingUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeResponse: [null, [Validators.required]],
    endPoint: [null, [Validators.required]],
    loginActeur: [null, [Validators.required]],
    requestId: [null, [Validators.required]],
    dateRequest: [null, [Validators.required]],
    dateResponse: [null, [Validators.required]],
    requestTr: [null, [Validators.required]],
    responseTr: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected trackingService: TrackingService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tracking }) => {
      if (!tracking.id) {
        const today = moment().startOf('day');
        tracking.dateRequest = today;
        tracking.dateResponse = today;
      }

      this.updateForm(tracking);
    });
  }

  updateForm(tracking: ITracking): void {
    this.editForm.patchValue({
      id: tracking.id,
      codeResponse: tracking.codeResponse,
      endPoint: tracking.endPoint,
      loginActeur: tracking.loginActeur,
      requestId: tracking.requestId,
      dateRequest: tracking.dateRequest ? tracking.dateRequest.format(DATE_TIME_FORMAT) : null,
      dateResponse: tracking.dateResponse ? tracking.dateResponse.format(DATE_TIME_FORMAT) : null,
      requestTr: tracking.requestTr,
      responseTr: tracking.responseTr
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('apiBoaExpressApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tracking = this.createFromForm();
    if (tracking.id !== undefined) {
      this.subscribeToSaveResponse(this.trackingService.update(tracking));
    } else {
      this.subscribeToSaveResponse(this.trackingService.create(tracking));
    }
  }

  private createFromForm(): ITracking {
    return {
      ...new Tracking(),
      id: this.editForm.get(['id'])!.value,
      codeResponse: this.editForm.get(['codeResponse'])!.value,
      endPoint: this.editForm.get(['endPoint'])!.value,
      loginActeur: this.editForm.get(['loginActeur'])!.value,
      requestId: this.editForm.get(['requestId'])!.value,
      dateRequest: this.editForm.get(['dateRequest'])!.value
        ? moment(this.editForm.get(['dateRequest'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dateResponse: this.editForm.get(['dateResponse'])!.value
        ? moment(this.editForm.get(['dateResponse'])!.value, DATE_TIME_FORMAT)
        : undefined,
      requestTr: this.editForm.get(['requestTr'])!.value,
      responseTr: this.editForm.get(['responseTr'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITracking>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
