import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'tracking',
        loadChildren: () => import('./tracking/tracking.module').then(m => m.ApiBoaExpressTrackingModule)
      },
      {
        path: 'param-end-point',
        loadChildren: () => import('./param-end-point/param-end-point.module').then(m => m.ApiBoaExpressParamEndPointModule)
      },
      {
        path: 'pays',
        loadChildren: () => import('./pays/pays.module').then(m => m.ApiBoaExpressPaysModule)
      },
      {
        path: 'transaction',
        loadChildren: () => import('./transaction/transaction.module').then(m => m.ApiBoaExpressTransactionModule)
      },
      {
        path: 'request-param',
        loadChildren: () => import('./request-param/request-param.module').then(m => m.ApiBoaExpressRequestParamModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class ApiBoaExpressEntityModule {}
