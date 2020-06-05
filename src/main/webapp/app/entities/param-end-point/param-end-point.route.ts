import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IParamEndPoint, ParamEndPoint } from 'app/shared/model/param-end-point.model';
import { ParamEndPointService } from './param-end-point.service';
import { ParamEndPointComponent } from './param-end-point.component';
import { ParamEndPointDetailComponent } from './param-end-point-detail.component';
import { ParamEndPointUpdateComponent } from './param-end-point-update.component';

@Injectable({ providedIn: 'root' })
export class ParamEndPointResolve implements Resolve<IParamEndPoint> {
  constructor(private service: ParamEndPointService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IParamEndPoint> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((paramEndPoint: HttpResponse<ParamEndPoint>) => {
          if (paramEndPoint.body) {
            return of(paramEndPoint.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ParamEndPoint());
  }
}

export const paramEndPointRoute: Routes = [
  {
    path: '',
    component: ParamEndPointComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'apiBoaExpressApp.paramEndPoint.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ParamEndPointDetailComponent,
    resolve: {
      paramEndPoint: ParamEndPointResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'apiBoaExpressApp.paramEndPoint.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ParamEndPointUpdateComponent,
    resolve: {
      paramEndPoint: ParamEndPointResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'apiBoaExpressApp.paramEndPoint.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ParamEndPointUpdateComponent,
    resolve: {
      paramEndPoint: ParamEndPointResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'apiBoaExpressApp.paramEndPoint.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
