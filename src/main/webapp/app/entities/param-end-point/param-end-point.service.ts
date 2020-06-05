import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IParamEndPoint } from 'app/shared/model/param-end-point.model';

type EntityResponseType = HttpResponse<IParamEndPoint>;
type EntityArrayResponseType = HttpResponse<IParamEndPoint[]>;

@Injectable({ providedIn: 'root' })
export class ParamEndPointService {
  public resourceUrl = SERVER_API_URL + 'api/param-end-points';

  constructor(protected http: HttpClient) {}

  create(paramEndPoint: IParamEndPoint): Observable<EntityResponseType> {
    return this.http.post<IParamEndPoint>(this.resourceUrl, paramEndPoint, { observe: 'response' });
  }

  update(paramEndPoint: IParamEndPoint): Observable<EntityResponseType> {
    return this.http.put<IParamEndPoint>(this.resourceUrl, paramEndPoint, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IParamEndPoint>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IParamEndPoint[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
