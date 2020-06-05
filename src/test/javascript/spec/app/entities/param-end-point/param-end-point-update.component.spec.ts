import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ApiBoaExpressTestModule } from '../../../test.module';
import { ParamEndPointUpdateComponent } from 'app/entities/param-end-point/param-end-point-update.component';
import { ParamEndPointService } from 'app/entities/param-end-point/param-end-point.service';
import { ParamEndPoint } from 'app/shared/model/param-end-point.model';

describe('Component Tests', () => {
  describe('ParamEndPoint Management Update Component', () => {
    let comp: ParamEndPointUpdateComponent;
    let fixture: ComponentFixture<ParamEndPointUpdateComponent>;
    let service: ParamEndPointService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ApiBoaExpressTestModule],
        declarations: [ParamEndPointUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ParamEndPointUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamEndPointUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamEndPointService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ParamEndPoint(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ParamEndPoint();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
