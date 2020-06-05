import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PaysService } from 'app/entities/pays/pays.service';
import { IPays, Pays } from 'app/shared/model/pays.model';

describe('Service Tests', () => {
  describe('Pays Service', () => {
    let injector: TestBed;
    let service: PaysService;
    let httpMock: HttpTestingController;
    let elemDefault: IPays;
    let expectedResult: IPays | IPays[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PaysService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Pays(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should return a list of Pays', () => {
        const returnedFromService = Object.assign(
          {
            pays: 'BBBBBB',
            isoAlpha2: 'BBBBBB',
            isoAlpha3: 'BBBBBB',
            isoNumerique: 'BBBBBB'
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
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
