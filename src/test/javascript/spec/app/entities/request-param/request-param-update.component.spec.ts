import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ApiBoaExpressTestModule } from '../../../test.module';
import { RequestParamUpdateComponent } from 'app/entities/request-param/request-param-update.component';
import { RequestParamService } from 'app/entities/request-param/request-param.service';
import { RequestParam } from 'app/shared/model/request-param.model';

describe('Component Tests', () => {
  describe('RequestParam Management Update Component', () => {
    let comp: RequestParamUpdateComponent;
    let fixture: ComponentFixture<RequestParamUpdateComponent>;
    let service: RequestParamService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ApiBoaExpressTestModule],
        declarations: [RequestParamUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(RequestParamUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RequestParamUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RequestParamService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RequestParam(123);
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
        const entity = new RequestParam();
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
