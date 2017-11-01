import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material';

@Component({
  selector: 'ytr-sign-in-form',
  templateUrl: './sign-in-form.component.html',
  styleUrls: ['./sign-in-form.component.scss']
})
export class SignInFormComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<SignInFormComponent>,
  ) { }

  ngOnInit() {
  }

  submit() {

    this.dialogRef.close();
  }

}
