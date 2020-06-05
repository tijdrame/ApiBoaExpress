import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TransactionService } from 'app/entities/transaction/transaction.service';
import { ITransaction, Transaction } from 'app/shared/model/transaction.model';

describe('Service Tests', () => {
  describe('Transaction Service', () => {
    let injector: TestBed;
    let service: TransactionService;
    let httpMock: HttpTestingController;
    let elemDefault: ITransaction;
    let expectedResult: ITransaction | ITransaction[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TransactionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Transaction(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Transaction', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Transaction()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Transaction', () => {
        const returnedFromService = Object.assign(
          {
            montant: 1,
            paysEnvoi: 'BBBBBB',
            paysDestination: 'BBBBBB',
            typeTransaction: 'BBBBBB',
            questionSecrete: 'BBBBBB',
            reponseSecrete: 'BBBBBB',
            referenceBancaire: 'BBBBBB',
            raisonTransfert: 'BBBBBB',
            canal: 'BBBBBB',
            senderName: 'BBBBBB',
            senderPrenom: 'BBBBBB',
            senderTelephone: 'BBBBBB',
            senderCompte: 'BBBBBB',
            beneficiaryName: 'BBBBBB',
            beneficiaryPrenom: 'BBBBBB',
            beneficiaryPhone: 'BBBBBB',
            numeroTransaction: 'BBBBBB',
            isConfirmed: true,
            codeConfim: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Transaction', () => {
        const returnedFromService = Object.assign(
          {
            montant: 1,
            paysEnvoi: 'BBBBBB',
            paysDestination: 'BBBBBB',
            typeTransaction: 'BBBBBB',
            questionSecrete: 'BBBBBB',
            reponseSecrete: 'BBBBBB',
            referenceBancaire: 'BBBBBB',
            raisonTransfert: 'BBBBBB',
            canal: 'BBBBBB',
            senderName: 'BBBBBB',
            senderPrenom: 'BBBBBB',
            senderTelephone: 'BBBBBB',
            senderCompte: 'BBBBBB',
            beneficiaryName: 'BBBBBB',
            beneficiaryPrenom: 'BBBBBB',
            beneficiaryPhone: 'BBBBBB',
            numeroTransaction: 'BBBBBB',
            isConfirmed: true,
            codeConfim: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Transaction', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
