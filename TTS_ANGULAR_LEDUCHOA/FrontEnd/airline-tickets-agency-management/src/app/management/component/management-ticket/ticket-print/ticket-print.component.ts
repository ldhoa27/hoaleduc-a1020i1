import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

import * as html2canvas from 'html2canvas';
import {element} from 'protractor';
import jsPDF from 'jspdf';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-ticket-print',
  templateUrl: './ticket-print.component.html',
  styleUrls: ['./ticket-print.component.css']
})
export class TicketPrintComponent implements OnInit {
  title = 'in';
  gender: string;
  money = this.data.ticketPrice + (this.data.tax * this.data.ticketPrice) + (this.data.ticketPrice * this.data.ticketTypePrice) - (this.data.ticketPrice * this.data.passengerTypePrice) + (this.data.plusBaggage * 1000);
  ticketBack: string;
  baggage: number;
  msg: string;

  constructor(public dialogRef: MatDialogRef<TicketPrintComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private toastr: ToastrService) {
    this.getGender();
    this.getBackGroup();
    this.getBaggage();
    this.getPlusBaby();
  }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  getGender() {
    if (this.data.passengerGender === 'Nam') {
      this.gender = 'Ông';
    } else {
      this.gender = 'Bà';
    }
  }

  getBaggage() {
    if (this.data.plusBaggage === 170000) {
      this.baggage = 15;
    } else if (this.data.plusBaggage === 100000) {
      this.baggage = 7;
    } else {
      this.baggage = 0;
    }
  }

  getBackGroup() {
    switch (this.data.flight.airline.airlineName) {
      case 'Jetstar Pacific Airlines':
        this.ticketBack = '#FF5200';
        break;
      case 'Bamboo Airways':
        this.ticketBack = '#1f9638';
        break;
      case 'Vietjet Air':
        this.ticketBack = '#ed1b24';
        break;
      case 'Vietnam Airlines':
        this.ticketBack = '#004282';
        break;
      case 'Japan Air':
        this.ticketBack = '#aaaaaa';
        break;
      case 'Mekong Airline':
        this.ticketBack = '#1a375f';
        break;
    }
  }

  in() {
    const e = document.getElementById('ve');
    // @ts-ignore
    var img = new Image();
    img.src = this.data.flight.airline.logo;
    // @ts-ignore
    html2canvas(e).then((canvas) => {
      const imgDta = canvas.toDataURL('image/png');
      const doc = new jsPDF();
      const imgHeight = canvas.height * 220 / canvas.width;
      // @ts-ignore
      doc.addImage(imgDta, 5, 10, 208, imgHeight);
      doc.save('Vé-'+ this.data.ticketCode+'.pdf');
      this.toastr.success('In thành công!!!', 'Thông báo :');
    });
  }

  err() {
    this.toastr.error('In thất bại!!!', 'Cảnh báo :');
  }

  getPlusBaby() {
    if (this.data.plusBaby === true) {
      this.msg = 'Có';
    } else {
      this.msg = 'Không';
    }
  }

}
