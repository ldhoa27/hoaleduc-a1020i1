import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {valiPhone} from "./ValiPhone";

interface Country {
  value: string,
  name: string
}

function valiPassword(c: AbstractControl): Validators | null {
  const v = c.value;

  if(v.password !== v.confirmPassword){
    return { 'valiPassword': true};
  }
  return null;
}

@Component({
  selector: 'app-register-final',
  templateUrl: './register-final.component.html',
  styleUrls: ['./register-final.component.css']
})
export class RegisterFinalComponent implements OnInit {
  registerForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    pwGroup: new FormGroup({
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      confirmPassword: new FormControl('', [Validators.required, Validators.minLength(6)])
    }, [valiPassword]),
    countryName: new FormControl('', [Validators.required]),
    age: new FormControl('', [Validators.min(18), Validators.required]),
    gender: new FormControl('', [Validators.required]),
    phone: new FormControl('', [valiPhone, Validators.required])
  });

  country: Country[] = [
    {
      value: 'VN',
      name: 'Viet Nam'
    },
    {
      value: 'US',
      name: 'America'
    },
    {
      value: 'LAO',
      name: 'Lao'
    },
    {
      value: 'ThaiLan',
      name: 'Thai Lan'
    }
  ];
  isSubmit: boolean = false;
  constructor() { }

  ngOnInit(): void {
  }

  get password(){
    return this.registerForm.get("pwGroup.password");
  }

  get confirmPassword(){
    return this.registerForm.get("pwGroup.confirmPassword");
  }

  onSubmit(){
    this.isSubmit = true;
    if (this.registerForm.invalid){
      return
    }
    alert("Create success");
  }
}

