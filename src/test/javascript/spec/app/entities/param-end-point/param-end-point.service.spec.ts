import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ParamEndPointService } from 'app/entities/param-end-point/param-end-point.service';
import { IParamEndPoint, ParamEndPoint } from 'app/shared/model/param-end-point.model';

describe('Service Tests', () => {
  describe('ParamEndPoint Service', () => {
    let injector: TestBed;
    let service: ParamEndPointService;
    let httpMock: HttpTestingController;
    let elemDefault: IParamEndPoint;
    let expectedResult: IParamEndPoint | IParamEndPoint[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ParamEndPointService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ParamEndPoint(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ParamEndPoint', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ParamEndPoint()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ParamEndPoint', () => {
        const returnedFromService = Object.assign(
          {
            codeParam: 'BBBBBB',
            endPoints: 'BBBBBB',
            attribute01: 'BBBBBB',
            attribute02: 'BBBBBB',
            attribute03: 'BBBBBB',
            attribute04: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ParamEndPoint', () => {
        const returnedFromService = Object.assign(
          {
            codeParam: 'BBBBBB',
            endPoints: 'BBBBBB',
            attribute01: 'BBBBBB',
            attribute02: 'BBBBBB',
            attribute03: 'BBBBBB',
            attribute04: 'BBBBBB'
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

      it('should delete a ParamEndPoint', () => {
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
