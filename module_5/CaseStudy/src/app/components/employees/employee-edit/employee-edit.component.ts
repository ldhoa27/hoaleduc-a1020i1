import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EmployeeService} from "../../../services/employee.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrls: ['./employee-edit.component.css']
})
export class EmployeeEditComponent implements OnInit {
  public formEditEmployee!: FormGroup;
  public employeeOfId!: string;
  constructor(
    public formBuilder: FormBuilder,
    public employeeService: EmployeeService,
    public router: Router,
    public activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.formEditEmployee = this.formBuilder.group({
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

    this.activatedRoute.params.subscribe(data =>{
      this.employeeOfId = data.id;
      this.employeeService.getEmployeeById(this.employeeOfId).subscribe(data =>{
        this.formEditEmployee.patchValue(data);
      })
    })
  }

  editEmployee() {
    this.employeeService.editEmployee(this.formEditEmployee.value, this.employeeOfId).subscribe(data =>{
      this.router.navigateByUrl('employee-list');
    })
  }
}
