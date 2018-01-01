import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material';
import { SignUpFormComponent } from '../sign-up-form/sign-up-form.component';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SignInFormComponentService } from './sign-in-form-component.service';

@Component({
  selector: 'ytr-sign-in-form',
  templateUrl: './sign-in-form.component.html',
  styleUrls: ['./sign-in-form.component.scss'],
  providers: [SignInFormComponentService]
})
export class SignInFormComponent implements OnInit {
  form: FormGroup;

  constructor(
    private dialogRef: MatDialogRef<SignInFormComponent>,
    private dialog: MatDialog,
    private fb: FormBuilder,
    private service: SignInFormComponentService
  ) { }

  ngOnInit() {
    this.form = this.createForm();
  }

  async submit() {
    await this.service.signIn(this.form.value);
    this.dialogRef.close();
  }

  openSignUpForm() {
    this.dialogRef.close();
    const dialog = this.dialog.open(SignUpFormComponent, {
      disableClose: true,
      width: '500px',
      position: {
        top: '15%'
      }
    });
  }

  private createForm(): FormGroup {
    return this.fb.group({
      email: '',
      password: '',
    });
  }
}
