import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SignInFormComponentService } from './sign-in-form-component.service';
import { Router } from '@angular/router';

@Component({
  selector: 'ytr-sign-in-form',
  templateUrl: './sign-in-form.component.html',
  styleUrls: ['./sign-in-form.component.scss'],
  providers: [SignInFormComponentService]
})
export class SignInFormComponent implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private service: SignInFormComponentService,
    private router: Router
  ) { }

  ngOnInit() {
    this.service.canActivate();
    this.form = this.createForm();
  }

  async submit() {
    await this.service.signIn(this.form.value);
    this.router.navigate(['/home']);
  }

  private createForm(): FormGroup {
    return this.fb.group({
      email: '',
      password: '',
    });
  }
}
