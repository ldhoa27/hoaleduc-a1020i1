import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ImageGalleryModule} from "./image-gallery/image-gallery.module";
import {ImgSlideComponent} from "./img-slider/img-slide/img-slide/img-slide.component";

@NgModule({
  declarations: [
    AppComponent,
    ImgSlideComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ImageGalleryModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
