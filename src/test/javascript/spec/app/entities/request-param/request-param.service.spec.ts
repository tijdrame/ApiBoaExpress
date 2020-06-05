import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RequestParamService } from 'app/entities/request-param/request-param.service';
import { IRequestParam, RequestParam } from 'app/shared/model/request-param.model';

describe('Service Tests', () => {
  describe('RequestParam Service', () => {
    let injector: TestBed;
    let service: RequestParamService;
    let httpMock: HttpTestingController;
    let elemDefault: IRequestParam;
    let expectedResult: IRequestParam | IRequestParam[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RequestParamService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new RequestParam(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RequestParam', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new RequestParam()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RequestParam', () => {
        const returnedFromService = Object.assign(
          {
            channelId: 'BBBBBB',
            userId: 'BBBBBB',
            transactionSecret: 'BBBBBB',
            requestId: 'BBBBBB',
            codePartenaire: 'BBBBBB',
            pays: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RequestParam', () => {
        const returnedFromService = Object.assign(
          {
            channelId: 'BBBBBB',
            userId: 'BBBBBB',
            transactionSecret: 'BBBBBB',
            requestId: 'BBBBBB',
            codePartenaire: 'BBBBBB',
            pays: 'BBBBBB'
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

      it('should delete a RequestParam', () => {
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
