import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { LANGUAGES } from 'app/core/language/language.constants';
import { User } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IPays } from 'app/shared/model/pays.model';
import { PaysService } from 'app/entities/pays/pays.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-user-mgmt-update',
  templateUrl: './user-management-update.component.html'
})
export class UserManagementUpdateComponent implements OnInit {
  user!: User;
  languages = LANGUAGES;
  authorities: string[] = [];
  isSaving = false;
  paysList: IPays[] = [];
  // itPays: IPays = {};

  editForm = this.fb.group({
    id: [],
    login: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(50), Validators.pattern('^[_.@A-Za-z0-9-]*')]],
    firstName: ['', [Validators.maxLength(50)]],
    lastName: ['', [Validators.maxLength(50)]],
    email: ['', [Validators.minLength(5), Validators.maxLength(254), Validators.email]],
    activated: [],
    langKey: [],
    authorities: [],
    pays: []
  });
 
  constructor(private userService: UserService, private route: ActivatedRoute, 
    private paysService: PaysService, private fb: FormBuilder) {
      
    }

  ngOnInit(): void {
    this.route.data.subscribe(({ user }) => {
      if (user) {
        this.user = user;
        if (this.user.id === undefined) {
          this.user.activated = true;
        }
        this.updateForm(user);
      }
    });
    this.userService.authorities().subscribe(authorities => {
      this.authorities = authorities;
    });
    this.paysService.queryBis()
    .subscribe(
      (res: HttpResponse<IPays[]>) => {
          this.paysList = res.body  || [];
      }
  );
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    alert("bef up user="+JSON.stringify(this.user.pays))
    this.isSaving = true;
    this.updateUser(this.user);
    if (this.user.id !== undefined) {
      alert("user="+JSON.stringify(this.user))
      this.userService.update(this.user).subscribe(
        () => this.onSaveSuccess(),
        () => this.onSaveError()
      );
    } else {
      this.userService.create(this.user).subscribe(
        () => this.onSaveSuccess(),
        () => this.onSaveError()
      );
    }
  }

  private updateForm(user: User): void {
    alert(JSON.stringify(user.pays))
    // this.itPays = user?.pays!;
    // alert("up form"+JSON.stringify(this.itPays?.pays))
    this.editForm.patchValue({
      id: user.id,
      login: user.login,
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      activated: user.activated,
      langKey: user.langKey,
      authorities: user.authorities,
      pays: user.pays
    });
  }

  private updateUser(user: User): void {
    alert("user up user="+JSON.stringify(this.user?.pays))
    user.login = this.editForm.get(['login'])!.value;
    user.firstName = this.editForm.get(['firstName'])!.value;
    user.lastName = this.editForm.get(['lastName'])!.value;
    user.email = this.editForm.get(['email'])!.value;
    user.activated = this.editForm.get(['activated'])!.value;
    user.langKey = this.editForm.get(['langKey'])!.value;
    user.authorities = this.editForm.get(['authorities'])!.value;
    this.paysService.find(this.editForm.get(['pays'])!.value).subscribe(
      (res: HttpResponse<IPays>) => {
        user.pays  = res.body  || {};
    }
    );
    // user.pays =  this.editForm.get(['pays'])!.value;
  }

  private onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError(): void {
    this.isSaving = false;
  }

  byId(item1: IPays, item2: IPays) : boolean {
    alert(item1.id)
    return item1.id === item2.id;
  }
}
