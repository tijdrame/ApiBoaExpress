import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITracking } from 'app/shared/model/tracking.model';

type EntityResponseType = HttpResponse<ITracking>;
type EntityArrayResponseType = HttpResponse<ITracking[]>;

@Injectable({ providedIn: 'root' })
export class TrackingService {
  public resourceUrl = SERVER_API_URL + 'api/trackings';

  constructor(protected http: HttpClient) {}

  create(tracking: ITracking): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tracking);
    return this.http
      .post<ITracking>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tracking: ITracking): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tracking);
    return this.http
      .put<ITracking>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITracking>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITracking[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryBis(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITracking[]>(
      // `${this.resourceUrl+"Bis"}/${req.dateDeb}/${req.dateFin}/${req.requestId}`, 
      `${this.resourceUrl+"Bis"}`, 
      { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tracking: ITracking): ITracking {
    const copy: ITracking = Object.assign({}, tracking, {
      dateRequest: tracking.dateRequest && tracking.dateRequest.isValid() ? tracking.dateRequest.toJSON() : undefined,
      dateResponse: tracking.dateResponse && tracking.dateResponse.isValid() ? tracking.dateResponse.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateRequest = res.body.dateRequest ? moment(res.body.dateRequest) : undefined;
      res.body.dateResponse = res.body.dateResponse ? moment(res.body.dateResponse) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tracking: ITracking) => {
        tracking.dateRequest = tracking.dateRequest ? moment(tracking.dateRequest) : undefined;
        tracking.dateResponse = tracking.dateResponse ? moment(tracking.dateResponse) : undefined;
      });
    }
    return res;
  }
}
