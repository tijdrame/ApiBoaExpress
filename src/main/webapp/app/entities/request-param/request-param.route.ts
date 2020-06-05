import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRequestParam, RequestParam } from 'app/shared/model/request-param.model';
import { RequestParamService } from './request-param.service';
import { RequestParamComponent } from './request-param.component';
import { RequestParamDetailComponent } from './request-param-detail.component';
import { RequestParamUpdateComponent } from './request-param-update.component';

@Injectable({ providedIn: 'root' })
export class RequestParamResolve implements Resolve<IRequestParam> {
  constructor(private service: RequestParamService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRequestParam> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((requestParam: HttpResponse<RequestParam>) => {
          if (requestParam.body) {
            return of(requestParam.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RequestParam());
  }
}

export const requestParamRoute: Routes = [
  {
    path: '',
    component: RequestParamComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'apiBoaExpressApp.requestParam.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RequestParamDetailComponent,
    resolve: {
      requestParam: RequestParamResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'apiBoaExpressApp.requestParam.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RequestParamUpdateComponent,
    resolve: {
      requestParam: RequestParamResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'apiBoaExpressApp.requestParam.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RequestParamUpdateComponent,
    resolve: {
      requestParam: RequestParamResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'apiBoaExpressApp.requestParam.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
