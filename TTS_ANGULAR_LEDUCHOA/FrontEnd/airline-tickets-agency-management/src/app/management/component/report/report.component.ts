import {Component, OnInit} from '@angular/core';
import {ReportService} from '../../../service/report/report.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
  setStartDate1 = '';
  setEndDate1 = '';
  setStartDate2 = '';
  setEndDate2 = '';
  getStartDate1 = '';
  getEndDate1 = '';
  getStartDate2 = '';
  getEndDate2 = '';
  startDate1 = '';
  endDate1 = '';
  startDate2 = '';
  endDate2 = '';
  typeChart: '';
  typeReport: '';
  hide = false;
  isDateNow1 = false;
  isTimeNow1 = false;
  isDateNow2 = false;
  isTimeNow2 = false;
  msgTyChart = '';
  msgTyReport = '';
  msgChooseTypeTime = '';
  msgChooseTimeNow = '';
  msgSetStartDate1 = '';
  msgStartDateLEndDate1 = '';
  msgStartDateLDateNow1 = '';
  msgSetEndDate1 = '';
  msgEndDateLDateNow1 = '';
  //
  msgChooseLastTime = '';
  msgSetStartDate2 = '';
  msgStartDateLEndDate2 = '';
  msgStartDateLDateNow2 = '';
  msgSetEndDate2 = '';
  msgEndDateLDateNow2 = '';
  // @ts-ignore
  thisWeekStart = new Date().getFullYear() + '-0' + (new Date().getMonth() + 1) + '-0' + (new Date().getDate() - 7);
  thisWeekEnd = new Date().getFullYear() + '-0' + (new Date().getMonth() + 1) + '-' + new Date().getDate();
  thisMonth = new Date().getFullYear() + '-' + (new Date().getMonth() + 1);
  thisYear = new Date().getFullYear();
  lastWeekStart = new Date().getFullYear() + '-0' + (new Date().getMonth() + 1) + '-' + (new Date().getDate() - 14);
  lastWeekEnd = new Date().getFullYear() + '-0' + (new Date().getMonth() + 1) + '-0' + (new Date().getDate() - 7);
  lastMonth = new Date().getFullYear() + '-' + new Date().getMonth();
  lastYear = new Date().getFullYear() - 1;
  dateNow = new Date().getFullYear() + '-0' + (new Date().getMonth() + 1) + '-' + new Date().getDate();

  constructor(private reportService: ReportService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  search() {
    this.checkValidate();
    // tslint:disable-next-line:max-line-length
    if (this.msgTyChart !== '' || this.msgTyReport !== '' || this.msgChooseTypeTime !== '' || this.msgChooseTimeNow !== '' || this.msgSetStartDate1 || this.msgStartDateLEndDate1 !== '' || this.msgStartDateLDateNow1 !== '' || this.msgSetEndDate1 !== '' || this.msgEndDateLDateNow1 !== '' || this.msgChooseLastTime !== '' || this.msgSetStartDate2 !== '' || this.msgStartDateLEndDate2 !== '' || this.msgSetEndDate2 !== '' ||  this.msgStartDateLDateNow2 !== '' || this. msgEndDateLDateNow2 !== '') {
      return;
    }
    this.setParameter();
    this.redirectStatistical();
  }

  checkValidate() {
    if (this.typeChart === undefined || this.typeChart === '') {
      this.msgTyChart = '*Vui l??ng ch???n ki???u b??o c??o';
    }
    if (this.typeReport === undefined || this.typeReport === '') {
      this.msgTyReport = '*Vui l??ng ch???n lo???i b??o c??o';
    }
    if (!this.isDateNow1 && !this.isTimeNow1 && !this.isDateNow2 && !this.isTimeNow2) {
      this.msgChooseTypeTime = '*Vui l??ng ch???n th???i gian ho???c nh???p th???i gian mu???n ????? b??o c??o';
    }
    if (this.isDateNow1 || this.isTimeNow1 || this.isDateNow2 || this.isTimeNow2) {
      this.msgChooseTypeTime = '';
    }
    if (this.isDateNow1) {
      if (this.startDate1 === '' || this.endDate1 === '') {
        this.msgChooseTimeNow = '*Vui l??ng ch???n th???i gian';
      }
    }
    if (this.isTimeNow1) {
      if (this.setStartDate1 === '') {
        this.msgSetStartDate1 = '*Vui l??ng nh???p t??? ng??y';
      }
      if (((Date.parse(this.setStartDate1) - Date.parse(this.setEndDate1) > 43100000))) {
        this.msgStartDateLEndDate1 = '*Vui l??ng ch???n ng??y b???t ?????u nh??? h??n ng??y k???t th??c.';
        return;
      }
      if (((Date.parse(this.setStartDate1) - Date.parse(this.dateNow) > 43100000))) {
        this.msgStartDateLDateNow1 = '*Vui l??ng ch???n ng??y nh??? h??n hi???n t???i.';
        return;
      }
      if (this.setEndDate1 === '') {
        this.msgSetEndDate1 = '*Vui l??ng nh???p ?????n ng??y';
      }
      if (((Date.parse(this.setEndDate1) - Date.parse(this.dateNow) > 43100000))) {
        this.msgEndDateLDateNow1 = '*Vui l??ng ch???n ng??y nh??? h??n hi???n t???i';
        return;
      }
    }
    if (this.isDateNow2) {
      if (this.startDate2 === '' || this.endDate2 === '') {
        this.msgChooseLastTime = '*Vui l??ng ch???n th???i gian';
      }
    }
    if (this.isTimeNow2) {
      if (this.setStartDate2 === '') {
        this.msgSetStartDate2 = '*Vui l??ng nh???p t??? ng??y';
      }
      if (((Date.parse(this.setStartDate2) - Date.parse(this.setEndDate2) > 43100000))) {
        this.msgStartDateLEndDate2 = '*Vui l??ng ch???n ng??y b???t ?????u nh??? h??n ng??y k???t th??c';
        return;
      }
      if (((Date.parse(this.setStartDate2) - Date.parse(this.dateNow) > 43100000))) {
        this.msgStartDateLDateNow2 = '*Vui l??ng ch???n ng??y b???t ?????u nh??? h??n ng??y hi???n t???i';
        return;
      }
      if (this.setEndDate2 === '') {
        this.msgSetEndDate2 = '*Vui l??ng nh???p ?????n ng??y';
      }
      if (((Date.parse(this.setEndDate2) - Date.parse(this.dateNow) > 43100000))) {
        this.msgEndDateLDateNow2 = '*Vui l??ng ch???n ng??y nh??? h??n hi???n t???i';
        return;
      }
    }
  }

  setParameter() {
    if (!this.hide) {
      if (this.isDateNow1 && this.isDateNow2) {
        this.reportService.setParameter(this.startDate1, this.endDate1, this.startDate2,
          this.endDate2, this.typeChart, this.typeReport);
      } else if (this.isDateNow1 && this.isTimeNow2) {
        this.getStartDate2 = this.setStartDate2;
        this.getEndDate2 = this.setEndDate2;
        this.reportService.setParameter(this.startDate1, this.endDate1, this.getStartDate2,
          this.getEndDate2, this.typeChart, this.typeReport);
      } else if (this.isTimeNow1 && this.isDateNow2) {
        this.getStartDate1 = this.setStartDate1;
        this.getEndDate1 = this.setEndDate1;
        this.reportService.setParameter(this.getStartDate1, this.getEndDate1, this.startDate2,
          this.endDate2, this.typeChart, this.typeReport);
      } else if (this.isTimeNow1 && this.isTimeNow1) {
        this.getStartDate1 = this.setStartDate1;
        this.getEndDate1 = this.setEndDate1;
        this.getStartDate2 = this.setStartDate2;
        this.getEndDate2 = this.setEndDate2;
        this.reportService.setParameter(this.getStartDate1, this.getEndDate1, this.getStartDate2,
          this.getEndDate2, this.typeChart, this.typeReport);
      }
    } else {
      if (this.isDateNow1) {
        this.reportService.setParameterTop(this.startDate1, this.endDate1, this.typeChart, this.typeReport);
      } else if (!this.isDateNow1) {
        this.getStartDate1 = this.setStartDate1;
        this.getEndDate1 = this.setEndDate1;
        this.reportService.setParameterTop(this.getStartDate1, this.getEndDate1, this.typeChart, this.typeReport);
      }
    }
  }

  redirectStatistical() {
    this.router.navigate(['management/statistical']);
  }

  chooseTypeChart(typeChart) {
    this.typeChart = typeChart.value;
  }

  chooseTypeReport(chooseTyeReport) {
    this.typeReport = chooseTyeReport.value;
    this.hide = false;
    switch (chooseTyeReport.value) {
      case 'revenue':
        this.hide = false;
        return;
      case 'employee':
        this.hide = true;
        return;
      case 'airline':
        this.hide = true;
        return;
    }
  }

// ch???n th???i gian n??y
  chooseTime1(chooseTime) {
    switch (chooseTime.value) {
      case 'thisMonth':
        this.getDateOfMonthNow();
        return;
      case 'thisYear':
        this.getDateOfYear(this.thisYear);
        return;
      case 'thisWeek':
        this.getDateOfWeekNow();
        return;
      default:
        return;
    }
  }

  // ch???n th???i gian tr?????c
  chooseTime2(chooseTime) {
    switch (chooseTime.value) {
      case 'lastMonth':
        this.getDateOfLastMonth();
        return;
      case 'lastYear':
        this.getDateOfLastYear(this.lastYear);
        return;
      case 'lastWeek':
        this.getDateOfLastWeek();
        return;
      default:
        return;
    }
  }

// th???i gian n??y
  getDateOfWeekNow() {
    const startDate = this.thisWeekStart;
    const endDate = this.thisWeekEnd;
    this.startDate1 = startDate;
    this.endDate1 = endDate;
  }

  getDateOfMonthNow() {
    const time = new Date(this.thisMonth);
    const startDate = this.formatDateToDb(time);
    const endDate = this.formatDateToDb(new Date(time.getFullYear(), time.getMonth() + 1, time.getDate()));
    this.startDate1 = startDate;
    this.endDate1 = endDate;
  }

  getDateOfYear(y) {
    const startDate = this.formatDateToDb(new Date(y, 0, 1));
    const endDate = this.formatDateToDb(new Date(+y + 1, 0, 1));
    this.startDate1 = startDate;
    this.endDate1 = endDate;
  }

// th???i gian tr?????c
  getDateOfLastWeek() {
    const startDate = this.lastWeekStart;
    const endDate = this.lastWeekEnd;
    this.startDate2 = startDate;
    this.endDate2 = endDate;
  }

  getDateOfLastMonth() {
    const time = new Date(this.lastMonth);
    const startDate = this.formatDateToDb(time);
    const endDate = this.formatDateToDb(new Date(time.getFullYear(), time.getMonth() + 1, time.getDate()));
    this.startDate2 = startDate;
    this.endDate2 = endDate;
  }

  getDateOfLastYear(y) {
    const startDate = this.formatDateToDb(new Date(y, 0, 1));
    const endDate = this.formatDateToDb(new Date(+y + 1, 0, 1));
    this.startDate2 = startDate;
    this.endDate2 = endDate;
  }

  formatDateToDb(date) {
    const d = new Date(date);
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    const year = d.getFullYear();
    if (month.length < 2) {
      month = '0' + month;
    }
    if (day.length < 2) {
      day = '0' + day;
    }
    return [year, month, day].join('-');
  }

  chooseDateNow1(choose) {
    if (choose === 'date1') {
      this.isDateNow1 = true;
      this.isTimeNow1 = false;
    } else {
      this.isTimeNow1 = true;
      this.isDateNow1 = false;
    }
  }

  chooseDateNow2(choose) {
    if (choose === 'date2') {
      this.isDateNow2 = true;
      this.isTimeNow2 = false;
    } else {
      this.isTimeNow2 = true;
      this.isDateNow2 = false;
    }
  }

  resetTyChart() {
    this.msgTyChart = '';
  }

  resetTypeReport() {
    this.msgTyReport = '';
  }

  resetTimeNow() {
    this.msgChooseTimeNow = '';
  }

  resetStartDate1() {
    this.msgSetStartDate1 = '';
    this.msgStartDateLEndDate1 = '';
    this.msgStartDateLDateNow1 = '';
  }

  resetEndDate1() {
    this.msgStartDateLEndDate1 = '';
    this.msgSetEndDate1 = '';
    this.msgEndDateLDateNow1 = '';
  }

  resetSetTime1() {
    this.msgSetStartDate1 = '';
    this.msgStartDateLEndDate1 = '';
    this.msgStartDateLDateNow1 = '';
    this.msgSetEndDate1 = '';
    this.msgEndDateLDateNow1 = '';
  }

  resetChooseTimeNow1() {
    this.msgChooseTimeNow = '';
  }

  resetChooseLastTime() {
    this.msgChooseLastTime = '';
  }

  resetSetTime2() {
    this.msgSetStartDate2 = '';
    this.msgStartDateLEndDate2 = '';
    this.msgStartDateLDateNow2 = '';
    this.msgSetEndDate2 = '';
    this.msgEndDateLDateNow2 = '';
  }

  resetStartDate2() {
    this.msgSetStartDate2 = '';
    this.msgStartDateLEndDate2 = '';
    this.msgStartDateLDateNow2 = '';
  }

  resetEndDate2() {
    this.msgSetEndDate2 = '';
    this.msgEndDateLDateNow2 = '';
    this.msgStartDateLEndDate2 = '';
  }
}
