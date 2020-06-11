import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { TransactionService } from 'app/entities/transaction/transaction.service';
import { HttpResponse } from '@angular/common/http';
import { ITotalTransac, IMontantTransac, IMontantCountry, IMontantPeriod } from 'app/shared/model/transaction.model';
import { Color } from 'ng2-charts';
import { ChartDataSets } from 'chart.js';
// import { isUndefined, isNullOrUndefined } from 'util';

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
  tot: ITotalTransac = {}
  amount: IMontantTransac = {}
  amountCountry: IMontantCountry[] = []
  public barChartOptions = {
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    },
    scaleShowVerticalLines: false,
    responsive: true
  };
  public barChartLabels: string[] = [] // ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
  public barChartType = 'bar';
  public barChartLegend = true;
  public barChartData: ChartDataSets[]
  /* [
    { "data": [160000, 50000], "label": "Côte d’Ivoire" },
    { "data": [30000, 30000], "label": "Sénégal" },
    // {"data":[160000,30000],"label":"Sénégal"},
  ] */
  /* [
    {data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A'},
    {data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B'}
  ]; */

  public pieChartLabels = ['Sales Q1', 'Sales Q2', 'Sales Q3', 'Sales Q4'];
  public pieChartData = [120, 150, 180, 90];
  public pieChartType = 'pie';

  colors: Color[] = [
    {
      backgroundColor: [
        'cyan', 'red',
        'green',
        'blue',
        'yellow',
        'grey',
        'pink',
        'brown',
        'orange',
        'purple',
        'white', 'black', 'azure'
      ]
    }
  ];
  myMap = new Map();

  constructor(private accountService: AccountService, private loginModalService: LoginModalService,
    private transactionService: TransactionService) {
    this.barChartData = [{ data: [] }]
  }

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));

    this.myMap.set('1', 'Janvier')
    this.myMap.set('2', 'Février')
    this.myMap.set('3', 'Mars')
    this.myMap.set('4', 'Avril')
    this.myMap.set('5', 'Mai')
    this.myMap.set('6', 'Juin')
    this.myMap.set('7', 'Juillet')
    this.myMap.set('8', 'Août')
    this.myMap.set('9', 'Septembre')
    this.myMap.set('10', 'Octobre')
    this.myMap.set('11', 'Novembre')
    this.myMap.set('12', 'Dècembre')
    this.transactionService.getNumberTransaction().subscribe(
      (res: HttpResponse<ITotalTransac>) => {
        this.tot = res.body!;
      }
    )

    this.transactionService.getMontantTransaction().subscribe(
      (res: HttpResponse<IMontantTransac>) => {
        this.amount = res.body!;
      }
    )
    this.pieChartLabels = [];
    this.pieChartData = [];
    this.transactionService.getMontantCountry().subscribe(
      (res: HttpResponse<IMontantCountry[]>) => {
        this.amountCountry = res.body!;
        if (typeof this.amountCountry['itemCountries'] !== 'undefined' && this.amountCountry['itemCountries'].length > 0) {
          this.amountCountry['itemCountries'].forEach((element: IMontantCountry) => {
            this.pieChartLabels.push(element['country']!)
            this.pieChartData.push(element['amount']!)
          });
        }
      }
    );


    this.barChartData = [{ data: []! }]

    this.transactionService.getMontantPeriod().subscribe(
      (res: HttpResponse<IMontantPeriod[]>) => {
        this.amountCountry = res.body!;
        if (typeof this.amountCountry['itemPeriods'] !== 'undefined' && this.amountCountry['itemPeriods'].length > 0) {
          let array: number[] = []
          const arrayPays: string[] = []
          // this.barChartData.shift();
          this.barChartData = [];
          this.amountCountry['itemPeriods'].forEach((element: IMontantPeriod) => {
            if (!arrayPays.includes(element['country']!))
              arrayPays.push(element['country']!);
            if (!this.barChartLabels.includes(this.myMap.get(element['month']!)))
              this.barChartLabels.push(this.myMap.get(element['month']!));
          });
          arrayPays.forEach((it: string) => {
            array = [];
            this.amountCountry['itemPeriods'].forEach((element: IMontantPeriod) => {

              if (it === element['country']!) {
                array.push(element['amount']!)
              }
            });
            this.barChartData.push({ data: array, label: it })


          });
          //
        }
      }
    );


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

  drawColumnChart(): void {
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
