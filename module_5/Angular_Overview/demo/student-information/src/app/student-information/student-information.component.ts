import { Component, OnInit } from '@angular/core';
import { IStudent } from '../IStudent';

@Component({
  selector: 'app-student-information',
  templateUrl: './student-information.component.html',
  styleUrls: ['./student-information.component.css']
})
export class StudentInformationComponent implements OnInit {
studentInformation: IStudent = {
  id: 1,
  name: 'Hoa',
  age: 22,
  address: 'Đà Nẵng',
  avatar: 'https://scr.vn/wp-content/uploads/2020/07/Avt-n%E1%BB%AF-t%C3%B3c-ng%E1%BA%AFn-cute-%C4%91%E1%BA%B9p-d%E1%BB%85-th%C6%B0%C6%A1nng.jpg',
  mark: 0
}
  constructor() { }

  ngOnInit(): void {
  }
  changeMark(mark: number){
    this.studentInformation.mark = mark;
  }

}
