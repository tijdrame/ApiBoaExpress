import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { TransactionService } from 'app/entities/transaction/transaction.service';
import { HttpResponse } from '@angular/common/http';
import { ITotalTransac, IMontantTransac } from 'app/shared/model/transaction.model';

// import CanvasJS from 'canvasjs';
// import * as CanvasJS from 'canvasjs';
// import * as CanvasJS from './CanvasJS';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;
  tot : ITotalTransac = {}
  amount : IMontantTransac = {}

  constructor(private accountService: AccountService, private loginModalService: LoginModalService,
    private transactionService: TransactionService) {}

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    
    this.transactionService.getNumberTransaction().subscribe(
      (res : HttpResponse<ITotalTransac>) => {
        this.tot = res.body!;
      }
    )

    this.transactionService.getMontantTransaction().subscribe(
      (res : HttpResponse<IMontantTransac>) => {
        this.amount = res.body!;
      }
    )
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }

  drawColumnChart() : void {
    /* const chart = new CanvasJS.Chart("chartContainer", {
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Basic Column Chart in Angular"
      },
      data: [{
        type: "column",
        dataPoints: [
          { y: 71, label: "Apple" },
          { y: 55, label: "Mango" },
          { y: 50, label: "Orange" },
          { y: 65, label: "Banana" },
          { y: 95, label: "Pineapple" },
          { y: 68, label: "Pears" },
          { y: 28, label: "Grapes" },
          { y: 34, label: "Lychee" },
          { y: 14, label: "Jackfruit" }
        ]
      }]
    });
      
    chart.render(); 
  */
  }
}
