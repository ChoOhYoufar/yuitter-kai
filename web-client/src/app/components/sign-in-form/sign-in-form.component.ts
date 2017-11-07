import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material';
import { SignUpFormComponent } from '../sign-up-form/sign-up-form.component';

@Component({
  selector: 'ytr-sign-in-form',
  templateUrl: './sign-in-form.component.html',
  styleUrls: ['./sign-in-form.component.scss']
})
export class SignInFormComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<SignInFormComponent>,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
  }

  submit() {
    this.dialogRef.close();
  }

  openSignUpForm() {
    this.dialogRef.close();
    let dialog = this.dialog.open(SignUpFormComponent, {
      disableClose: true,
      width: '500px',
      position: {
        top: '15%'
      }
    });
  }

}
