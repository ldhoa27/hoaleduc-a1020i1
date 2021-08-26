import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Article} from "../article/article.component";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-add-new',
  templateUrl: './add-new.component.html',
  styleUrls: ['./add-new.component.css']
})
export class AddNewComponent implements OnInit {

  @Input() articlesChild : Array<Article> | undefined;

  @Output() countChanged: EventEmitter<Array<Article>> = new EventEmitter();
  a: any;
  private title: string | undefined;
  private url: string | undefined;

  constructor() { }

  ngOnInit(): void {
  }

  addArticle(){
    let a = new Article();
    a.title = this.title;
    a.url = this.url;
    // @ts-ignore
    this.articlesChild.push(a);
    this.countChanged.emit(this.articlesChild);
  }

}
