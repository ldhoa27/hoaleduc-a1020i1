import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentInformationComponent } from './student-information/student-information.component';
import { StudentListComponent } from './student-list/student-list.component';

@NgModule({
  declarations: [
    AppComponent,
    StudentInformationComponent,
    StudentListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
