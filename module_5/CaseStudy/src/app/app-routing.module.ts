import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { EmployeeListComponent } from './components/employees/employee-list/employee-list.component';
import {CommonModule} from "@angular/common";
import { EmployeeAddComponent } from './components/employees/employee-add/employee-add.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgxPaginationModule} from 'ngx-pagination';
import { EmployeeEditComponent } from './components/employees/employee-edit/employee-edit.component';
const routes: Routes = [
  {
    path: '', component: HomeComponent
  },
  {
    path: 'employee-list', component: EmployeeListComponent
  },
  {
    path: 'employee-add', component: EmployeeAddComponent
  },
  {
    path: 'employee-edit/:id', component: EmployeeEditComponent
  },
  {
    path: '**', component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [CommonModule, Ng2SearchPipeModule, RouterModule.forRoot(routes), FormsModule, NgxPaginationModule, ReactiveFormsModule],
  exports: [RouterModule],
  declarations: [
    HomeComponent,
    PageNotFoundComponent,
    EmployeeListComponent,
    EmployeeAddComponent,
    EmployeeEditComponent

  ]
})
export class AppRoutingModule { }
