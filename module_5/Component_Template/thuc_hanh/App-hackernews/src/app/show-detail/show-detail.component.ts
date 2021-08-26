import {Component, Input, OnInit} from '@angular/core';
import {Article} from '../article/article.component';
@Component({
  selector: 'app-show-detail',
  templateUrl: './show-detail.component.html',
  styleUrls: ['./show-detail.component.css']
})
export class ShowDetailComponent implements OnInit {
  @Input() articlesChild : Article[] | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
