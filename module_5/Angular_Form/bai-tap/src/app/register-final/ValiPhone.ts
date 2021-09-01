import {AbstractControl, ValidationErrors} from "@angular/forms";

export function valiPhone(control: AbstractControl): ValidationErrors | null {
  const regex =  /^\+84\d{9,10}$/;
  const v=control.value;
  if(v === ''){
    return {'valiPhone': false}
  }
  if (v.match(regex) == null){
    return { 'valiPhone': true}
  }
  return null;
}
