import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRequestParam } from 'app/shared/model/request-param.model';

type EntityResponseType = HttpResponse<IRequestParam>;
type EntityArrayResponseType = HttpResponse<IRequestParam[]>;

@Injectable({ providedIn: 'root' })
export class RequestParamService {
  public resourceUrl = SERVER_API_URL + 'api/request-params';

  constructor(protected http: HttpClient) {}

  create(requestParam: IRequestParam): Observable<EntityResponseType> {
    return this.http.post<IRequestParam>(this.resourceUrl, requestParam, { observe: 'response' });
  }

  update(requestParam: IRequestParam): Observable<EntityResponseType> {
    return this.http.put<IRequestParam>(this.resourceUrl, requestParam, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRequestParam>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRequestParam[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
