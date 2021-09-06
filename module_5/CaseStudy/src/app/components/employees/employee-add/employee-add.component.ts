import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EmployeeService} from "../../../services/employee.service";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-employee-add',
  templateUrl: './employee-add.component.html',
  styleUrls: ['./employee-add.component.css']
})
export class EmployeeAddComponent implements OnInit {
  public formAddNewEmployee!: FormGroup;
  constructor(
    public formBuilder: FormBuilder,
    public employeeService: EmployeeService,
    public router: Router
  ) { }

  ngOnInit() {
    this.formAddNewEmployee = this.formBuilder.group({
      id:['', [Validators.required, Validators.pattern('^(NV-)[\\d]{4}$')]],
      fullname: ['', [Validators.required]],
      position: ['', [Validators.required]],
      academiLevel: ['', [Validators.required]],
      part: ['', [Validators.required]],
      dateOfBirth: ['', [Validators.required]],
      identityNumber: ['', [Validators.required, Validators.pattern('^[0-9]{9}$')]],
      salary: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.pattern('^(090|091|([\(]84[\)][\+]90)|([\(]84[\)][\+]91))[0-9]{7}$')]],
      address: ['', [Validators.required]],
    })
  }

  addNewEmployee() {
    this.employeeService.addNewEmployee(this.formAddNewEmployee.value).subscribe(data =>{
      this.router.navigateByUrl('employee-list');
    })
  }
}
