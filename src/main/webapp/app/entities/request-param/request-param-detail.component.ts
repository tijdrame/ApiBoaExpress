import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRequestParam } from 'app/shared/model/request-param.model';

@Component({
  selector: 'jhi-request-param-detail',
  templateUrl: './request-param-detail.component.html'
})
export class RequestParamDetailComponent implements OnInit {
  requestParam: IRequestParam | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requestParam }) => (this.requestParam = requestParam));
  }

  previousState(): void {
    window.history.back();
  }
}
