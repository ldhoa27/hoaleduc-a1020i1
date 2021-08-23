import { Component } from '@angular/core';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent {
  output: number= 0;
  first: number= 0;
  second: number= 0;
  operator = '+';
  onFirstChange(value: number) {
    this.first = Number(value);
  }
  onSecondChange(value: number) {
    this.second = Number(value);
  }
  onSelectChange(value: string) {
    this.operator = value;
  }
  calculate() {
    switch (this.operator) {
      case '+':
        this.output = this.first + this.second;
        break;
      case '-':
        this.output = this.first - this.second;
        break;
      case '*':
        this.output = this.first * this.second;
        break;
      case '/':
        this.output = this.first / this.second;
        break;
    }
  }
}