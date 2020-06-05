import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ApiBoaExpressTestModule } from '../../../test.module';
import { ParamEndPointComponent } from 'app/entities/param-end-point/param-end-point.component';
import { ParamEndPointService } from 'app/entities/param-end-point/param-end-point.service';
import { ParamEndPoint } from 'app/shared/model/param-end-point.model';

describe('Component Tests', () => {
  describe('ParamEndPoint Management Component', () => {
    let comp: ParamEndPointComponent;
    let fixture: ComponentFixture<ParamEndPointComponent>;
    let service: ParamEndPointService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ApiBoaExpressTestModule],
        declarations: [ParamEndPointComponent]
      })
        .overrideTemplate(ParamEndPointComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamEndPointComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamEndPointService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ParamEndPoint(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.paramEndPoints && comp.paramEndPoints[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
