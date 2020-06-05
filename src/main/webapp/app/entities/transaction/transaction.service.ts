import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITransaction, ITotalTransac, IMontantTransac } from 'app/shared/model/transaction.model';

type EntityResponseType = HttpResponse<ITransaction>;
type EntityResponseType2 = HttpResponse<ITotalTransac>;
type EntityResponseType3 = HttpResponse<IMontantTransac>;
type EntityArrayResponseType = HttpResponse<ITransaction[]>;

@Injectable({ providedIn: 'root' })
export class TransactionService {
  public resourceUrl = SERVER_API_URL + 'api/transactions';

  constructor(protected http: HttpClient) {}

  create(transaction: ITransaction): Observable<EntityResponseType> {
    return this.http.post<ITransaction>(this.resourceUrl, transaction, { observe: 'response' });
  }

  update(transaction: ITransaction): Observable<EntityResponseType> {
    return this.http.put<ITransaction>(this.resourceUrl, transaction, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITransaction>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITransaction[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  queryBis(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITransaction[]>(
      `${this.resourceUrl+"Bis"}`, 
      { params: options, observe: 'response' });
  }

  getNumberTransaction(): Observable<EntityResponseType2> {
    return this.http.get<ITotalTransac>(`${this.resourceUrl+"Number"}`, { observe: 'response' });
  }

  getMontantTransaction(): Observable<EntityResponseType3> {
    return this.http.get<IMontantTransac>(`${this.resourceUrl+"Montant"}`, { observe: 'response' });
  }
}
