import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LikeComponent } from './like/like.component';
import { ArticleComponent } from './article/article.component';
import { AddNewComponent } from './add-new/add-new.component';
import {FormsModule} from "@angular/forms";
import { NavbarComponent } from './navbar/navbar.component';
import { ShowDetailComponent } from './show-detail/show-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    LikeComponent,
    ArticleComponent,
    AddNewComponent,
    NavbarComponent,
    ShowDetailComponent
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
