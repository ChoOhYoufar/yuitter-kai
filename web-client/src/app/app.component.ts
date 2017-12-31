import { Component, OnInit, VERSION } from '@angular/core';
import { MatDialog } from '@angular/material';
import { SignInFormComponent } from './components/sign-in-form/sign-in-form.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  ngOnInit() {
  }
}
