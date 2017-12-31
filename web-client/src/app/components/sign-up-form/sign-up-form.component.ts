import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material';
import { SignInFormComponent } from '../sign-in-form/sign-in-form.component';
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
    private dialog: MatDialog,
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

  openSignUpForm() {
    this.dialogRef.close();
    this.dialog.open(SignInFormComponent, {
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
    })
  }
}
