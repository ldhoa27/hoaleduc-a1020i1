import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

import {comparePassword} from './validation/compare-password';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ToastrService} from 'ngx-toastr';
import {Password} from "../../../../model/password";
import {Employee} from "../../../../model/employee";
import {EmployeeService} from "../../../../service/employee/employee.service";
import {PasswordService} from "../../../../service/password/password.service";


@Component({
  selector: 'app-admin-change-password',
  templateUrl: './admin-change-password.component.html',
  styleUrls: ['./admin-change-password.component.css']
})
export class AdminChangePasswordComponent implements OnInit {
  admin: Employee;
  password: Password;
  id: number;
  name : string;
  checkPassForm = new FormGroup({
    oldPassword: new FormControl('', [Validators.required, Validators.pattern('^\\w{5,}$')]),
    newPassword: new FormControl('', [Validators.required, Validators.pattern('^\\w{5,}$')]),
    confirmPassword: new FormControl('')
  }, comparePassword);

  constructor(@Inject(MAT_DIALOG_DATA) public data, public dialogRef: MatDialogRef<AdminChangePasswordComponent>,
              private employeeService: EmployeeService, private toast: ToastrService, private passwordService : PasswordService
  ) {
  }

  ngOnInit(): void {
    this.id = this.data.id;
    this.name =this.data.name;
    console.log(this.id);
    console.log(this.name);
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  changePassword() {
    this.password = this.checkPassForm.value;
    const oldPassword = this.password.oldPassword;
    const newPassword = this.password.newPassword;
    const confirmPassword = this.password.confirmPassword;
    this.checkValidate(oldPassword);
    if (oldPassword === '') {
      this.toast.warning('Chưa nhập mật khẩu', 'Chú ý !');
    } else {
      if (!this.checkValidate(oldPassword)) {
        this.toast.warning('Mật khẩu phải là kí tự chữ a -z không dấu , kí tự số 0-9 , ít nhất 5 kí tự ', 'Chú ý');
      } else {
        if (newPassword === '') {
          this.toast.warning('Chưa nhập mật khẩu mới', 'Chú ý !');
        } else {
          if (!this.checkValidate(newPassword)) {
            this.toast.warning('Mật khẩu phải là kí tự chữ a -z không dấu , kí tự số 0-9 , ít nhất 5 kí tự ', 'Chú ý');
          } else {
            if (confirmPassword === '') {
              this.toast.warning('Chưa nhập lại mật khẩu mới', 'Chú ý');
            } else {
              if (newPassword === confirmPassword) {
                this.passwordService.sendPassword(this.id, this.password).subscribe(data => {
                  this.closeDialog();
                  this.toast.success(data.msg, 'Chú ý !');
                  console.log(data);
                }, error => {
                  this.toast.warning(error.error.msg, 'Chú ý !');
                  console.log(error);
                });
              } else {
                this.toast.warning('Mật khẩu không trùng khớp', 'Chú ý');
              }
            }
          }
        }
      }
    }
  }
  checkValidate(a: string) {
    const check = /^\w{5,}$/;
    return check.test(a);
  }
}
