import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material';
import { SignInFormComponent } from '../sign-in-form/sign-in-form.component';

@Component({
  selector: 'app-sign-up-form',
  templateUrl: './sign-up-form.component.html',
  styleUrls: ['./sign-up-form.component.scss']
})
export class SignUpFormComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<SignUpFormComponent>,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
  }

  submit() {
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

}
