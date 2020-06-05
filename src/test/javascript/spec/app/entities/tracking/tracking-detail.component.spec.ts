import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { ApiBoaExpressTestModule } from '../../../test.module';
import { TrackingDetailComponent } from 'app/entities/tracking/tracking-detail.component';
import { Tracking } from 'app/shared/model/tracking.model';

describe('Component Tests', () => {
  describe('Tracking Management Detail Component', () => {
    let comp: TrackingDetailComponent;
    let fixture: ComponentFixture<TrackingDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ tracking: new Tracking(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ApiBoaExpressTestModule],
        declarations: [TrackingDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TrackingDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TrackingDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load tracking on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tracking).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
