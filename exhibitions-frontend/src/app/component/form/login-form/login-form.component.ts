import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  model = new LoginModel("username","password")

  submitted = false;

  onSubmit() { this.submitted = true; }

}


class LoginModel {
  constructor(
    public username:string,
    public password:string
  ) {

  }
}