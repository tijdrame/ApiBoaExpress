import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITransaction, Transaction } from 'app/shared/model/transaction.model';
import { TransactionService } from './transaction.service';

@Component({
  selector: 'jhi-transaction-update',
  templateUrl: './transaction-update.component.html'
})
export class TransactionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    montant: [null, [Validators.required]],
    paysEnvoi: [null, [Validators.required]],
    paysDestination: [null, [Validators.required]],
    typeTransaction: [null, [Validators.required]],
    questionSecrete: [null, [Validators.required]],
    reponseSecrete: [null, [Validators.required]],
    referenceBancaire: [null, [Validators.required]],
    raisonTransfert: [],
    canal: [],
    senderName: [null, [Validators.required]],
    senderPrenom: [],
    senderTelephone: [null, [Validators.required]],
    senderCompte: [null, [Validators.required]],
    beneficiaryName: [null, [Validators.required]],
    beneficiaryPrenom: [],
    beneficiaryPhone: [null, [Validators.required]],
    numeroTransaction: [],
    isConfirmed: [],
    codeConfim: []
  });

  constructor(protected transactionService: TransactionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ transaction }) => {
      this.updateForm(transaction);
    });
  }

  updateForm(transaction: ITransaction): void {
    this.editForm.patchValue({
      id: transaction.id,
      montant: transaction.montant,
      paysEnvoi: transaction.paysEnvoi,
      paysDestination: transaction.paysDestination,
      typeTransaction: transaction.typeTransaction,
      questionSecrete: transaction.questionSecrete,
      reponseSecrete: transaction.reponseSecrete,
      referenceBancaire: transaction.referenceBancaire,
      raisonTransfert: transaction.raisonTransfert,
      canal: transaction.canal,
      senderName: transaction.senderName,
      senderPrenom: transaction.senderPrenom,
      senderTelephone: transaction.senderTelephone,
      senderCompte: transaction.senderCompte,
      beneficiaryName: transaction.beneficiaryName,
      beneficiaryPrenom: transaction.beneficiaryPrenom,
      beneficiaryPhone: transaction.beneficiaryPhone,
      numeroTransaction: transaction.numeroTransaction,
      isConfirmed: transaction.isConfirmed,
      codeConfim: transaction.codeConfim
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const transaction = this.createFromForm();
    if (transaction.id !== undefined) {
      this.subscribeToSaveResponse(this.transactionService.update(transaction));
    } else {
      this.subscribeToSaveResponse(this.transactionService.create(transaction));
    }
  }

  private createFromForm(): ITransaction {
    return {
      ...new Transaction(),
      id: this.editForm.get(['id'])!.value,
      montant: this.editForm.get(['montant'])!.value,
      paysEnvoi: this.editForm.get(['paysEnvoi'])!.value,
      paysDestination: this.editForm.get(['paysDestination'])!.value,
      typeTransaction: this.editForm.get(['typeTransaction'])!.value,
      questionSecrete: this.editForm.get(['questionSecrete'])!.value,
      reponseSecrete: this.editForm.get(['reponseSecrete'])!.value,
      referenceBancaire: this.editForm.get(['referenceBancaire'])!.value,
      raisonTransfert: this.editForm.get(['raisonTransfert'])!.value,
      canal: this.editForm.get(['canal'])!.value,
      senderName: this.editForm.get(['senderName'])!.value,
      senderPrenom: this.editForm.get(['senderPrenom'])!.value,
      senderTelephone: this.editForm.get(['senderTelephone'])!.value,
      senderCompte: this.editForm.get(['senderCompte'])!.value,
      beneficiaryName: this.editForm.get(['beneficiaryName'])!.value,
      beneficiaryPrenom: this.editForm.get(['beneficiaryPrenom'])!.value,
      beneficiaryPhone: this.editForm.get(['beneficiaryPhone'])!.value,
      numeroTransaction: this.editForm.get(['numeroTransaction'])!.value,
      isConfirmed: this.editForm.get(['isConfirmed'])!.value,
      codeConfim: this.editForm.get(['codeConfim'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransaction>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
