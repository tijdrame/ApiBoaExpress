import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITransaction } from 'app/shared/model/transaction.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { TransactionService } from './transaction.service';
import { TransactionDeleteDialogComponent } from './transaction-delete-dialog.component';
import { isUndefined } from 'util';
import * as jsPDF from 'jspdf';
import * as html2canvas from 'html2canvas';
import { IPays } from 'app/shared/model/pays.model';
import { PaysService } from '../pays/pays.service';

@Component({
  selector: 'jhi-transaction',
  templateUrl: './transaction.component.html'
})
export class TransactionComponent implements OnInit, OnDestroy {
  transactions?: ITransaction[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number; 
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  refBancaire = "";
  sendertel ="";
  senderCompte ="";
  numTransation ="";
  totalMontant =0;
  totalFrais =0;
  dateDeb = '';
  dateFin = '';
  paysEnvoi : IPays = {};
  paysDestination : IPays = {};
  paysList: IPays[] = [];


  constructor(
    protected transactionService: TransactionService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    private paysService: PaysService,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number ): void {
    const pageToLoad: number = page || this.page;

    this.transactionService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ITransaction[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      ); 
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      // this.loadPage();
    }); 
    this.registerChangeInTransactions(); 
    this.paysService.query()
    .subscribe(
      (res: HttpResponse<IPays[]>) => {
          this.paysList = res.body  || [];
      },
  );
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITransaction): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTransactions(): void {
    this.eventSubscriber = this.eventManager.subscribe('transactionListModification', () => this.loadPage());
  }

  delete(transaction: ITransaction): void {
    const modalRef = this.modalService.open(TransactionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.transaction = transaction;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ITransaction[] | null, headers: HttpHeaders, page: number): void {
    this.totalMontant= 0;
    this.totalFrais= 0;
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/transaction'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.transactions = data || [];
    if (this.transactions.length>0) {
      this.transactions.forEach((it: ITransaction) => {
        const x = it.montant?it.montant:0;
        const y = it.montantFrais?it.montantFrais:0;
        this.totalMontant += x;
        this.totalFrais += y;
      });
    }
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }

  search(page?: number): void {
    const pageToLoad: number = page || this.page;
    let payEnv = this.paysEnvoi?.isoAlpha2!;
    let payDest = this.paysDestination?.isoAlpha2!;
    if(this.refBancaire===""||isUndefined(this.refBancaire))this.refBancaire=" ";
      if(this.senderCompte===""||isUndefined(this.senderCompte))this.senderCompte=" ";
      if(this.sendertel===""||isUndefined(this.sendertel))this.sendertel=" ";
      if(this.numTransation===""||isUndefined(this.numTransation))this.numTransation=" ";
      if(isUndefined(payEnv)) payEnv = " ";
      if(isUndefined(payDest)) payDest = " ";
    this.transactionService
    .queryBis({
      refBancaire: this.refBancaire, 
      sendertel: this.sendertel,
      senderCompte: this.senderCompte, 
      numTransation: this.numTransation, 
      dateDebut : this.dateDeb,
      dateFin : this.dateFin,
      paysEnvoi : payEnv,
      paysDestination : payDest,
      page: pageToLoad - 1,
      size: this.itemsPerPage,
      sort: this.sort()})
    .subscribe(
      (res: HttpResponse<ITransaction[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
      () => this.onError()
    );
    this.refBancaire = this.refBancaire.trim();
    this.senderCompte = this.senderCompte.trim();
    this.sendertel = this.sendertel.trim();
    this.numTransation = this.numTransation.trim();
  }

  downLoadPdf(): void {
    const data = document.getElementById ('myComponent') as HTMLElement;
        html2canvas(data).then(canvas =>{
            // console.log('innnnnnn');
            const imgWidth = 208;
            // let pageHeight = 295;
            const imgHeight = canvas.height * imgWidth / canvas.width;
            // let heightLeft = imgHeight;
 
            const contentDataURL = canvas.toDataURL('image/png');
            const pdf = new jsPDF('l', 'mm', 'a4');
            pdf.addImage(contentDataURL, 'PNG', 10, 20, imgWidth, imgHeight);
            pdf.save('listTransactions.pdf');
        }); 
  }

}
