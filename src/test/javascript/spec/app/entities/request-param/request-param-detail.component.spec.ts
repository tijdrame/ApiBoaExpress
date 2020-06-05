import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ApiBoaExpressTestModule } from '../../../test.module';
import { RequestParamDetailComponent } from 'app/entities/request-param/request-param-detail.component';
import { RequestParam } from 'app/shared/model/request-param.model';

describe('Component Tests', () => {
  describe('RequestParam Management Detail Component', () => {
    let comp: RequestParamDetailComponent;
    let fixture: ComponentFixture<RequestParamDetailComponent>;
    const route = ({ data: of({ requestParam: new RequestParam(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ApiBoaExpressTestModule],
        declarations: [RequestParamDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(RequestParamDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RequestParamDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load requestParam on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.requestParam).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
