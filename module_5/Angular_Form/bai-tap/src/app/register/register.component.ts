import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

function comparePassword(c: AbstractControl) {
  const v = c.value;
  return (v.password === v.confirmPassword) ? null : {
    passwordnotmatch: true
  };
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    pwGroup: new FormGroup({
      password: new FormControl(''),
      confirmPassword: new FormControl('')
    }, {validators: comparePassword})
  });
  constructor(private fb: FormBuilder) {

  }

  ngOnInit(): void {
    // update form state
    this.registerForm.patchValue({
      email: 'info@example.com'
    });
  }

  get r(){
    return this.registerForm.controls;
  }
  onSubmit() {}
}
