import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRequestParam, RequestParam } from 'app/shared/model/request-param.model';
import { RequestParamService } from './request-param.service';

@Component({
  selector: 'jhi-request-param-update',
  templateUrl: './request-param-update.component.html'
})
export class RequestParamUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    channelId: [null, [Validators.required]],
    userId: [null, [Validators.required]],
    transactionSecret: [null, [Validators.required]],
    requestId: [null, [Validators.required]],
    codePartenaire: [null, [Validators.required]],
    pays: [null, [Validators.required]]
  });

  constructor(protected requestParamService: RequestParamService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requestParam }) => {
      this.updateForm(requestParam);
    });
  }

  updateForm(requestParam: IRequestParam): void {
    this.editForm.patchValue({
      id: requestParam.id,
      channelId: requestParam.channelId,
      userId: requestParam.userId,
      transactionSecret: requestParam.transactionSecret,
      requestId: requestParam.requestId,
      codePartenaire: requestParam.codePartenaire,
      pays: requestParam.pays
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const requestParam = this.createFromForm();
    if (requestParam.id !== undefined) {
      this.subscribeToSaveResponse(this.requestParamService.update(requestParam));
    } else {
      this.subscribeToSaveResponse(this.requestParamService.create(requestParam));
    }
  }

  private createFromForm(): IRequestParam {
    return {
      ...new RequestParam(),
      id: this.editForm.get(['id'])!.value,
      channelId: this.editForm.get(['channelId'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      transactionSecret: this.editForm.get(['transactionSecret'])!.value,
      requestId: this.editForm.get(['requestId'])!.value,
      codePartenaire: this.editForm.get(['codePartenaire'])!.value,
      pays: this.editForm.get(['pays'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequestParam>>): void {
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
