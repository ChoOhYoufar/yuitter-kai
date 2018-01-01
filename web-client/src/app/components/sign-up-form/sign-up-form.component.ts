import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SignUpFormComponentService } from './sign-up-form-component.service';

@Component({
  selector: 'app-sign-up-form',
  templateUrl: './sign-up-form.component.html',
  styleUrls: ['./sign-up-form.component.scss'],
  providers: [SignUpFormComponentService]
})
export class SignUpFormComponent implements OnInit {
  form: FormGroup;

  constructor(
    private dialogRef: MatDialogRef<SignUpFormComponent>,
    private fb: FormBuilder,
    private service: SignUpFormComponentService,
  ) { }

  ngOnInit() {
    this.form = this.createForm();
  }

  async submit() {
    await this.service.signUp(this.form.value);
    this.dialogRef.close();
  }

  private createForm(): FormGroup {
    return this.fb.group({
      email: '',
      password: '',
    })
  }
}
