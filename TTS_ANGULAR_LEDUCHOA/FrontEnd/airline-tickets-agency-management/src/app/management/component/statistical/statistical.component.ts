import {Component, ViewChild, OnInit} from '@angular/core';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import {
  // top
  ApexStates,
  // Bar
  ApexPlotOptions,
  ApexFill,
  // circle
  ApexNonAxisChartSeries,
  ApexResponsive,
  // lie
  ChartComponent,
  ApexAxisChartSeries,
  ApexChart,
  ApexDataLabels,
  ApexStroke,
  ApexMarkers,
  ApexYAxis,
  ApexGrid,
  ApexTitleSubtitle,
  ApexLegend,
  ApexXAxis,
  // Top
  ApexTooltip
} from 'ng-apexcharts';
import {Report} from '../../../model/report';
import {ReportService} from '../../../service/report/report.service';
import {FontBase64} from '../../../font-base64';
import {Router} from '@angular/router';

export interface ChartOptions {
  // bar
  seriesBar: ApexAxisChartSeries;
  plotOptions: ApexPlotOptions;
  fill: ApexFill;
  // circle
  seriesPie: ApexNonAxisChartSeries;
  responsive: ApexResponsive[];
  labelsPie: string[];
  // lie
  seriesLine: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  stroke: ApexStroke;
  dataLabels: ApexDataLabels;
  markers: ApexMarkers;
  tooltip: any;
  yaxis: ApexYAxis;
  grid: ApexGrid;
  legend: ApexLegend;
  title: ApexTitleSubtitle;
  // Top bar
  seriesBarTop: ApexAxisChartSeries;
  colors: string[];
  subtitle: ApexTitleSubtitle;
  // Top circle
  seriesCircleTop: ApexNonAxisChartSeries;
  labelsTopCircle: any;
  labelsCircle: string[];
  // Top Line
  seriesLineTop: ApexAxisChartSeries;
}

@Component({
  selector: 'app-statistical',
  templateUrl: './statistical.component.html',
  styleUrls: ['./statistical.component.css']
})
export class StatisticalComponent implements OnInit {
  @ViewChild('chart') chart: ChartComponent;
  public chartOptions: Partial<ChartOptions>;
  statisticalChart1: Report[] = [];
  statisticalChart2: Report[] = [];
  startDate1: string;
  endDate1: string;
  startDate2: string;
  endDate2: string;
  typeChart: string;
  typeReport: string;
  totalPie1 = 0;
  totalPie2 = 0;
  isLine = false;
  isCircle = false;
  isBar = false;
  isTopBar = false;
  isTopCircle = false;
  isTopLine = false;
  totalMoney1 = 0;
  totalMoney2 = 0;
  isEmployee = false;
  isAirline = false;
  formatter = new Intl.NumberFormat('it-IT', {
    style: 'currency',
    currency: 'VND',
  });
  topEmployee = 'Top nh??n vi??n B??n ???????c nhi???u v?? nh???t';
  topAirline = 'Top h??ng h??ng kh??ng b??n ???????c nhi???u v?? nh???t';

  constructor(private reportService: ReportService,
              private fontBase: FontBase64,
              private router: Router) {
  }

  ngOnInit(): void {
    this.showChart();
  }

  redirectReport() {
    this.router.navigate(['management/report']);
  }

  showChart() {
    this.typeChart = this.reportService.getParameterTypeChart();
    this.typeReport = this.reportService.getParameterTypeReport();
    if (this.typeReport === 'revenue') {
      switch (this.typeChart) {
        case 'circle':
          this.isCircle = true;
          this.paintChartTypeCircle();
          this.setChartPie1();
          this.setChartPie2();
          return;
        case 'line':
          this.isLine = true;
          this.paintChartTypeLine();
          this.setChartLine1();
          this.setChartLine2();
          return;
        case 'bar':
          this.isBar = true;
          this.paintChartTypeBar();
          this.setChartBar1();
          this.setChartBar2();
          return;
        default:
          this.isBar = false;
          this.isLine = false;
          this.isCircle = false;
          return;
      }
    }
    if (this.typeReport === 'employee' || this.typeReport === 'airline') {
      switch (this.typeChart) {
        case 'bar':
          this.isTopBar = true;
          this.paintChartTypeBarTop();
          this.setChartBarTop();
          return;
        case 'circle':
          this.isTopCircle = true;
          this.paintChartTypeCircleTop();
          this.setChartCircleTop();
          return;
        case 'line':
          this.isTopLine = true;
          this.paintChartTypeLineTop();
          this.setChartLineTop();
          return;
        default:
          this.isTopCircle = false;
          this.isTopLine = false;
          this.isTopBar = false;
          this.isBar = false;
          this.isLine = false;
          this.isCircle = false;
          return;
      }
    }
  }

// line
  setChartLine1() {
    this.startDate1 = this.reportService.getParameterStartDate1();
    this.endDate1 = this.reportService.getParameterEndDate1();
    this.reportService.getListStatisticalOneDate1(this.startDate1, this.endDate1).subscribe(chart1 => {
      this.statisticalChart1 = chart1;
      let i;
      for (i = 0; i < this.statisticalChart1.length; i++) {
        this.totalMoney1 += Number(this.statisticalChart1[i].totalMoney);
        // @ts-ignore
        this.chartOptions.seriesLine[0].data.push(this.statisticalChart1[i].totalMoney);
      }
    });
  }

  setChartLine2() {
    this.startDate2 = this.reportService.getParameterStartDate2();
    this.endDate2 = this.reportService.getParameterEndDate2();
    this.reportService.getListStatisticalOneDate2(this.startDate2, this.endDate2).subscribe(chart2 => {
      this.statisticalChart2 = chart2;
      let i;
      for (i = 0; i < this.statisticalChart2.length; i++) {
        this.totalMoney2 += Number(this.statisticalChart1[i].totalMoney);
        // @ts-ignore
        this.chartOptions.seriesLine[1].data.push(this.statisticalChart2[i].totalMoney);
      }
    });
  }

  paintChartTypeLine() {
    this.chartOptions = {
      seriesLine: [
        {
          name: 'Th???i gian 1',
          data: []
        },
        {
          name: 'Th???i gian 2',
          data: []
        },
      ],
      chart: {
        height: 350,
        type: 'line'
      },
      dataLabels: {
        enabled: true
      },
      stroke: {
        width: 5,
        curve: 'straight',
        dashArray: [0, 8, 5]
      },
      title: {
        text: 'Doanh Thu',
        align: 'center'
      },
      legend: {
        tooltipHoverFormatter(val, opts) {
          return (
            val +
            ' - <strong>' +
            opts.w.globals.series[opts.seriesIndex][opts.dataPointIndex] +
            '</strong>'
          );
        }
      },
      markers: {
        size: 0,
        hover: {
          sizeOffset: 6
        }
      },
      xaxis: {
        labels: {
          trim: false
        },
        categories: [
          'Ng??y 1',
          'Ng??y 2',
          'ng??y 3',
          'Ng??y 4',
          'Ng??y 5',
          'Ng??y 6',
          'Ng??y 7',
          'Ng??y 8',
          'Ng??y 9',
          'Ng??y 10',
          'Ng??y 11',
          'Ng??y 12',
          'Ng??y 13',
          'Ng??y 14',
          'Ng??y 15'
        ]
      },
      tooltip: {
        y: [
          {
            title: {
              formatter(val) {
                return val + ' (mins)';
              }
            }
          },
          {
            title: {
              formatter(val) {
                return val + ' per session';
              }
            }
          },
          {
            title: {
              formatter(val) {
                return val;
              }
            }
          }
        ]
      },
      grid: {
        borderColor: '#f1f1f1'
      }
    };
  }

// pie
  setChartPie1() {
    this.startDate1 = this.reportService.getParameterStartDate1();
    this.endDate1 = this.reportService.getParameterEndDate1();
    this.reportService.getListStatisticalOneDate1(this.startDate1, this.endDate1).subscribe(chart1 => {
      this.statisticalChart1 = chart1;
      let i;
      for (i = 0; i < this.statisticalChart1.length; i++) {
        // tslint:disable-next-line:radix
        this.totalPie1 += Number(this.statisticalChart1[i].totalMoney);
      }
      this.chartOptions.seriesPie.push(this.totalPie1);
      this.totalMoney1 = this.totalPie1;
    });
  }

  setChartPie2() {
    this.startDate2 = this.reportService.getParameterStartDate2();
    this.endDate2 = this.reportService.getParameterEndDate2();
    this.reportService.getListStatisticalOneDate2(this.startDate2, this.endDate2).subscribe(chart2 => {
      this.statisticalChart2 = chart2;
      let i;
      for (i = 0; i < this.statisticalChart2.length; i++) {
        // tslint:disable-next-line:radix
        this.totalPie2 += Number(this.statisticalChart2[i].totalMoney);
      }
      this.chartOptions.seriesPie.push(this.totalPie2);
      this.totalMoney2 = this.totalPie2;
    });
  }

  paintChartTypeCircle() {
    this.chartOptions = {
      seriesPie: [],
      chart: {
        // width: 480,
        type: 'pie'
      },
      labelsPie: ['Th???i gian 1', 'Th???i gian 2'],
      responsive: [
        {
          breakpoint: 480,
          options: {
            chart: {
              // width: 200
            },
            legend: {
              position: 'bottom'
            }
          }
        }
      ]
    };
  }

  // bar
  setChartBar1() {
    this.startDate1 = this.reportService.getParameterStartDate1();
    this.endDate1 = this.reportService.getParameterEndDate1();
    this.reportService.getListStatisticalOneDate1(this.startDate1, this.endDate1).subscribe(chart1 => {
      this.statisticalChart1 = chart1;
      let i;
      for (i = 0; i < this.statisticalChart1.length; i++) {
        this.totalMoney1 += Number(this.statisticalChart1[i].totalMoney);
        // @ts-ignore
        this.chartOptions.seriesBar[0].data.push(this.statisticalChart1[i].totalMoney);
        this.chartOptions.seriesBar[0].name = 'Th???i gian 1';
      }
    });
  }

  setChartBar2() {
    this.startDate2 = this.reportService.getParameterStartDate2();
    this.endDate2 = this.reportService.getParameterEndDate2();
    this.reportService.getListStatisticalOneDate2(this.startDate2, this.endDate2).subscribe(chart2 => {
      this.statisticalChart2 = chart2;
      let i;
      for (i = 0; i < this.statisticalChart2.length; i++) {
        this.totalMoney2 += Number(this.statisticalChart1[i].totalMoney);
        // @ts-ignore
        this.chartOptions.seriesBar[1].data.push(this.statisticalChart2[i].totalMoney);
        this.chartOptions.seriesBar[1].name = 'Th???i gian 2';
      }
    });
  }

  paintChartTypeBar() {
    this.chartOptions = {
      seriesBar: [
        {
          name: '',
          data: []
        },
        {
          name: '',
          data: []
        },
      ],
      chart: {
        type: 'bar',
        height: 350
      },
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: '55%',
        }
      },
      dataLabels: {
        enabled: false
      },
      stroke: {
        show: true,
        width: 2,
        colors: ['transparent']
      },
      xaxis: {
        categories: [
          'Ng??y 1',
          'Ng??y 2',
          'Ng??y 3',
          'Ng??y 4',
          'Ng??y 5',
          'Ng??y 6',
          'Ng??y 7'
        ]
      },
      fill: {
        opacity: 1
      },
      tooltip: {
        y: {
          formatter(val) {
            return val + ' VND';
          }
        }
      }
    };
  }

// Top bar
  setChartBarTop() {
    this.startDate1 = this.reportService.getParameterStartDate1();
    this.endDate1 = this.reportService.getParameterEndDate1();
    this.typeReport = this.reportService.getParameterTypeReport();
    console.log(this.typeReport);
    if (this.typeReport === 'employee') {
      this.reportService.getTop5Employee(this.startDate1, this.endDate1).subscribe(chart1 => {
        this.statisticalChart1 = chart1;
        let i;
        for (i = 0; i < this.statisticalChart1.length; i++) {
          // @ts-ignore
          this.chartOptions.seriesBarTop[0].data.push(this.statisticalChart1[i].quantity);
          this.chartOptions.xaxis.categories.push(this.statisticalChart1[i].employeeName);
        }
      });
      this.chartOptions.title.text = 'Top nh??n vi??n b??n ???????c nhi???u v?? nh???t';
    }
    if (this.typeReport === 'airline') {
      this.reportService.getTop5Airline(this.startDate1, this.endDate1).subscribe(chart1 => {
        this.statisticalChart1 = chart1;
        let i;
        for (i = 0; i < this.statisticalChart1.length; i++) {
          // @ts-ignore
          this.chartOptions.seriesBarTop[0].data.push(this.statisticalChart1[i].quantity);
          this.chartOptions.xaxis.categories.push(this.statisticalChart1[i].airlineName);
        }
      });
      this.chartOptions.title.text = 'Top h??ng m??y bay b??n ???????c nhi???u v?? nh???t';
    }
  }

  paintChartTypeBarTop() {
    this.chartOptions = {
      seriesBarTop: [
        {
          data: []
        }
      ],
      chart: {
        type: 'bar',
        // height: 400
      },
      plotOptions: {
        bar: {
          barHeight: '100%',
          distributed: true,
          horizontal: true,
          dataLabels: {
            position: 'bottom'
          }
        }
      },
      colors: [
        '#33b2df',
        '#546E7A',
        '#d4526e',
        '#13d8aa',
        '#A5978B',
        '#2b908f',
        '#f9a3a4',
        '#90ee7e',
        '#f48024',
        '#69d2e7'
      ],
      dataLabels: {
        enabled: true,
        textAnchor: 'start',
        style: {
          colors: ['#fff']
        },
        formatter(val, opt) {
          return opt.w.globals.labels[opt.dataPointIndex] + ':  ' + val;
        },
        offsetX: 0,
        dropShadow: {
          enabled: true
        }
      },
      stroke: {
        width: 1,
        colors: ['#fff']
      },
      xaxis: {
        categories: []
      },
      yaxis: {
        labels: {
          show: false
        }
      },
      title: {
        text: '',
        align: 'center',
        floating: true
      },
      tooltip: {
        theme: 'dark',
        x: {
          show: false
        },
        y: {
          title: {
            formatter() {
              return '';
            }
          }
        }
      }
    };
  }

// Top Circle
  setChartCircleTop() {
    this.startDate1 = this.reportService.getParameterStartDate1();
    this.endDate1 = this.reportService.getParameterEndDate1();
    this.typeReport = this.reportService.getParameterTypeReport();
    if (this.typeReport === 'employee') {
      this.isEmployee = true;
      this.reportService.getTop5Employee(this.startDate1, this.endDate1).subscribe(chart1 => {
        this.statisticalChart1 = chart1;
        let i;
        for (i = 0; i < this.statisticalChart1.length; i++) {
          // @ts-ignore
          this.chartOptions.seriesCircleTop.push(Number(this.statisticalChart1[i].quantity));
          this.chartOptions.labelsCircle.push(this.statisticalChart1[i].employeeName);
        }
      });
    }
    if (this.typeReport === 'airline') {
      this.isAirline = true;
      this.reportService.getTop5Airline(this.startDate1, this.endDate1).subscribe(chart1 => {
        this.statisticalChart1 = chart1;
        let i;
        for (i = 0; i < this.statisticalChart1.length; i++) {
          // @ts-ignore
          this.chartOptions.seriesCircleTop.push(Number(this.statisticalChart1[i].quantity));
          this.chartOptions.labelsCircle.push(this.statisticalChart1[i].airlineName);
        }
      });
    }
  }

  paintChartTypeCircleTop() {
    this.chartOptions = {
      seriesCircleTop: [],
      chart: {
        // width: 380,
        type: 'pie'
      },
      labelsCircle: [],
      responsive: [
        {
          breakpoint: 480,
          options: {
            chart: {
              width: 200
            },
            legend: {
              position: 'bottom'
            }
          }
        }
      ]
    };
  }

  // Top Line
  setChartLineTop() {
    this.startDate1 = this.reportService.getParameterStartDate1();
    this.endDate1 = this.reportService.getParameterEndDate1();
    this.typeReport = this.reportService.getParameterTypeReport();
    if (this.typeReport === 'employee') {
      this.reportService.getTop5Employee(this.startDate1, this.endDate1).subscribe(chart1 => {
        this.statisticalChart1 = chart1;
        let i;
        for (i = 0; i < this.statisticalChart1.length; i++) {
          // @ts-ignore
          this.chartOptions.seriesLineTop[i].name = (this.statisticalChart1[i].employeeName);
          // @ts-ignore
          this.chartOptions.seriesLineTop[i].data.push(Number(this.statisticalChart1[i].quantity));
        }
      });
      this.chartOptions.title.text = this.topEmployee;
    }
    if (this.typeReport === 'airline') {
      this.reportService.getTop5Airline(this.startDate1, this.endDate1).subscribe(chart1 => {
        this.statisticalChart1 = chart1;
        let i;
        for (i = 0; i < this.statisticalChart1.length; i++) {
          // @ts-ignore
          this.chartOptions.seriesLineTop[i].name = (this.statisticalChart1[i].airlineName);
          // @ts-ignore
          this.chartOptions.seriesLineTop[i].data.push(Number(this.statisticalChart1[i].quantity));
        }
      });
      this.chartOptions.title.text = this.topAirline;
    }
  }

  paintChartTypeLineTop() {
    this.chartOptions = {
      seriesLineTop: [
        {
          name: '',
          data: []
        },
        {
          name: '',
          data: []
        },
        {
          name: '',
          data: []
        },
        {
          name: '',
          data: []
        },
        {
          name: '',
          data: []
        }
      ],
      chart: {
        height: 350,
        type: 'line',
        zoom: {
          enabled: false
        }
      },
      dataLabels: {
        enabled: true
      },
      stroke: {
        curve: 'straight'
      },
      title: {
        text: '',
        align: 'center',
        floating: true
      },
      grid: {
        row: {
          colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
          opacity: 0.5
        }
      },
    };
  }

  htmlToPDF() {
    const doc = new jsPDF('l', 'mm', 'a4');
    doc.addFileToVFS('src/assets/font/Calibri Light.ttf', this.fontBase.font);
    doc.addFont('src/assets/font/Calibri Light.ttf', 'Calibri Light', 'normal');
    doc.setFont('Calibri Light');
    if (this.typeReport === 'revenue') {
      const head = [['Th???i gian', 'T???ng ti???n']];
      const body = [];
      const number1 = 1;
      this.statisticalChart1.forEach(s => {

        // @ts-ignore
        const temp = [s.flightDate, this.formatter.format(s.totalMoney)];
        body.push(temp);
      });
      this.statisticalChart2.forEach(s => {

        // @ts-ignore
        const temp = [s.flightDate, this.formatter.format(s.totalMoney)];
        body.push(temp);
      });
      doc.setFontSize(25);
      doc.setTextColor('red');
      doc.text('B??o c??o doanh thu', 110, 10);
      doc.text('C??ng ty AirLines gi?? r???', 90, 20);
      doc.setFontSize(20);
      doc.setTextColor('black');
      doc.text('Th??ng tin b??o c??o', 15, 30);
      doc.setFontSize(14);
      // tslint:disable-next-line:max-line-length
      doc.text('th???i gian 1: t??? ng??y: ' + this.startDate1 + ' ?????n ng??y: ' + this.endDate1 + ' t???ng ti???n: ' + this.formatter.format(this.totalPie1) + ' VN??', 25, 40);
      // tslint:disable-next-line:max-line-length
      doc.text('th???i gian 2: t??? ng??y: ' + this.startDate2 + ' ?????n ng??y: ' + this.endDate2 + ' t???ng ti???n: ' + this.formatter.format(this.totalPie2) + ' VN??', 25, 50);
      doc.setFontSize(20);
      // doc.text('Danh s??ch thu???c', 15, 70);
      doc.setFontSize(14);
      autoTable(doc, {
        styles: {
          font: 'Calibri Light',
          fontSize: 14
        },
        margin: {top: 55},
        head,
        body,
        didDrawCell: (data) => {
        },
      });
      doc.setTextColor('red');
      // doc.text('T???ng ti???n : ' + this.formatter.format(), 230, this.statisticalChart1.length * 8.5 + 105);
      doc.setTextColor('black');
      doc.text('???? N???ng , Ng??y 23 Th??ng 01 N??m 2022', 160, this.statisticalChart1.length * 8.5 + 135);
      // @ts-ignore
      doc.text('Ng?????i l???p phi???u', 180, this.statisticalChart1.length * 8.5 + 140);
      doc.text('HoaLD', 190, this.statisticalChart1.length * 8.5 + 145);
      doc.text('L?? ?????c H??a', 180, this.statisticalChart1.length * 8.5 + 150);
      doc.save('B??o c??o doanh thu.pdf');
    }
    if (this.typeReport === 'employee') {
      const head = [['T??n nh??n vi??n', 'S??? l?????ng']];
      const body = [];
      const number1 = 1;
      this.statisticalChart1.forEach(s => {

        // @ts-ignore
        const temp = [s.employeeName, s.quantity];
        body.push(temp);
      });
      doc.setFontSize(25);
      doc.setTextColor('red');
      doc.text('B??o c??o top nh??n vi??n b??n ???????c nhi???u v?? nh???t', 60, 10);
      doc.text('C??ng ty AirLine v?? gi?? r???', 100, 20);
      doc.setFontSize(20);
      doc.setTextColor('black');
      doc.text('Th??ng tin b??o c??o', 15, 30);
      doc.setFontSize(14);
      // tslint:disable-next-line:max-line-length
      doc.text('th???i gian : t??? ng??y: ' + this.startDate1 + ' ?????n ng??y: ' + this.endDate1 , 25, 40);
      doc.setFontSize(20);
      // doc.text('Danh s??ch thu???c', 15, 70);
      doc.setFontSize(14);
      autoTable(doc, {
        styles: {
          font: 'Calibri Light',
          fontSize: 14
        },
        margin: {top: 50},
        head,
        body,
        didDrawCell: (data) => {
        },
      });
      doc.setTextColor('red');
      doc.setTextColor('black');
      doc.text('???? N???ng , Ng??y 23 Th??ng 01 N??m 2022', 180, this.statisticalChart1.length * 8.5 + 80);
      // @ts-ignore
      doc.text('Ng?????i l???p phi???u', 200, this.statisticalChart1.length * 8.5 + 90);
      doc.text('HoaLD', 210, this.statisticalChart1.length * 8.5 + 95);
      doc.text('L?? ?????c H??a', 200, this.statisticalChart1.length * 8.5 + 100);
      doc.save('B??o c??o Top nh??n vi??n.pdf');
    }
    if (this.typeReport === 'airline') {
      const head = [['T??n h??ng m??y bay', 'S??? l?????ng']];
      const body = [];
      const number1 = 1;
      this.statisticalChart1.forEach(s => {

        // @ts-ignore
        const temp = [s.airlineName, s.quantity];
        body.push(temp);
      });
      doc.setFontSize(25);
      doc.setTextColor('red');
      doc.text('B??o c??o top 5 h??ng m??y bay b??n ???????c nhi???u v?? nh???t', 60, 10);
      doc.text('C??ng ty AirLine v?? gi?? r???', 105, 20);
      doc.setFontSize(20);
      doc.setTextColor('black');
      doc.text('Th??ng tin b??o c??o', 15, 30);
      doc.setFontSize(14);
      // tslint:disable-next-line:max-line-length
      doc.text('th???i gian : t??? ng??y: ' + this.startDate1 + ' ?????n ng??y: ' + this.endDate1 , 25, 40);
      doc.setFontSize(20);
      // doc.text('Danh s??ch thu???c', 15, 70);
      doc.setFontSize(14);
      autoTable(doc, {
        styles: {
          font: 'Calibri Light',
          fontSize: 14
        },
        margin: {top: 50},
        head,
        body,
        didDrawCell: (data) => {
        },
      });
      doc.setTextColor('red');
      doc.setTextColor('black');
      doc.text('???? N???ng , Ng??y 23 Th??ng 01 N??m 2022', 180, this.statisticalChart1.length * 8.5 + 80);
      // @ts-ignore
      doc.text('Ng?????i l???p phi???u', 200, this.statisticalChart1.length * 8.5 + 90);
      doc.text('HoaLD', 210, this.statisticalChart1.length * 8.5 + 95);
      doc.text('L?? ?????c H??a', 200, this.statisticalChart1.length * 8.5 + 100);
      doc.save('B??o c??o Top h??ng m??y bay.pdf');
    }
  }
}
