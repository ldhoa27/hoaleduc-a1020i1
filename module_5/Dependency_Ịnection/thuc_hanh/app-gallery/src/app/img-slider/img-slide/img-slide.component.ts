import {Component, ContentChildren, OnInit, QueryList, TemplateRef, ViewChild} from '@angular/core';

@Component({
  selector: 'app-img-slide',
  templateUrl: './img-slide.component.html',
  styleUrls: ['./img-slide.component.css']
})
export class ImgSlideComponent implements OnInit {

  @ContentChildren(ImgSlideComponent) slides!: QueryList<ImgSlideComponent>;
  component!: ImgSlideComponent ;
  activeIndex = 0;
  constructor() { }

  ngOnInit() {
  }

  ngAfterContentInit() {
    console.log(this.slides);
    this.component = this.slides.first;
  }

  previos() {
    if (this.activeIndex > 0) {
      this.activeIndex--;
    }
    this.component = this.slides.toArray()[this.activeIndex];
  }
  next() {
    if (this.activeIndex < this.slides.length - 1) {
      this.activeIndex++;
    }
    this.component = this.slides.toArray()[this.activeIndex];
  }
}
