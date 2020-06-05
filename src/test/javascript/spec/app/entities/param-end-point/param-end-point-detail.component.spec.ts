import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ApiBoaExpressTestModule } from '../../../test.module';
import { ParamEndPointDetailComponent } from 'app/entities/param-end-point/param-end-point-detail.component';
import { ParamEndPoint } from 'app/shared/model/param-end-point.model';

describe('Component Tests', () => {
  describe('ParamEndPoint Management Detail Component', () => {
    let comp: ParamEndPointDetailComponent;
    let fixture: ComponentFixture<ParamEndPointDetailComponent>;
    const route = ({ data: of({ paramEndPoint: new ParamEndPoint(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ApiBoaExpressTestModule],
        declarations: [ParamEndPointDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ParamEndPointDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParamEndPointDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load paramEndPoint on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paramEndPoint).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
