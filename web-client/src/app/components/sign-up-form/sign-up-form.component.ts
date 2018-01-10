import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SignUpFormComponentService } from './sign-up-form-component.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up-form',
  templateUrl: './sign-up-form.component.html',
  styleUrls: ['./sign-up-form.component.scss'],
  providers: [SignUpFormComponentService]
})
export class SignUpFormComponent implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private service: SignUpFormComponentService,
    private router: Router,
  ) { }

  ngOnInit() {
    this.form = this.createForm();
  }

  async submit() {
    await this.service.signUp(this.form.value);
    this.router.navigate(['/home']);
  }

  private createForm(): FormGroup {
    return this.fb.group({
      email: '',
      password: '',
    })
  }
}
