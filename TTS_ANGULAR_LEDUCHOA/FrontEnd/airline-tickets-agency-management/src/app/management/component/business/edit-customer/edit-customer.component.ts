import { Component, OnInit } from '@angular/core';
import {CustomerService} from '../../../../service/customer/customer.service';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {Customer} from '../../../../model/customer/customer';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})
export class EditCustomerComponent implements OnInit {
  customerForm: FormGroup;
  customer: Customer;
  idCustomer: any;
  msgEmail = "";
  msgPassport = "";
  msgDate = "";
  constructor(private customerService: CustomerService,
              private activatedRoute: ActivatedRoute,
              private toast: ToastrService,
              private router: Router) {
    this.activatedRoute.paramMap.subscribe((paramMap:ParamMap)=>{
      this.idCustomer = paramMap.get('id');
    });
    this.customerService.findById(this.idCustomer).subscribe(data =>{
      this.customer = data;
      // this.customerForm.patchValue(this.customer);
      // console.log(this.customerForm);
      this.customerForm = new FormGroup({
          customerId: new FormControl(this.customer.customerId),
          customerCode: new FormControl(this.customer.customerCode),
          customerName: new FormControl(this.customer.customerName, [Validators.required, Validators.minLength(6),Validators.maxLength(20)]),
          customerAddress: new FormControl(this.customer.customerAddress,Validators.required),
          customerBirthday: new FormControl(this.customer.customerBirthday, [Validators.required,,this.validAge]),
          customerGender: new FormControl(this.customer.customerGender,Validators.required),
          customerEmail: new FormControl(this.customer.customerEmail,[Validators.required,Validators.email]),
          customerPhone: new FormControl(this.customer.customerPhone, [Validators.required,Validators.pattern(/^[0-9]{10}$/)]),
          customerPassport: new FormControl(this.customer.customerPassport,[Validators.required,Validators.pattern(/^[0-9]{9}$|^[0-9]{12}$/)])
        }
      );
    });


  }

  validationMessage = {
    customerName: [
      {type: 'required', message: 'T??n kh??ng ???????c ????? tr???ng'},
      {type: 'minlength',message: 'T??n t???i thi???u 6 k?? t???'},
      {type: 'maxlength',message: 'T??n t???i ??a 20 k?? t???'}
    ],
    customerAddress:[
      {type: 'required', message: '?????a ch??? kh??ng ???????c ????? tr???ng'},
    ],
    customerBirthday:[
      {type: 'required', message: 'Ng??y sinh kh??ng ???????c ????? tr???ng'},
      {type: 'validAge', message: 'Tu???i ph???i l???n h??n 18'}
    ],
    customerEmail: [
      {type: 'required', message: 'Email kh??ng ???????c ????? tr???ng.'},
      {type: 'email', message: 'Vui l??ng nh???p ????ng ?????nh d???ng.'}
    ],
    customerPhone: [
      {type: 'required', message: '??i???n tho???i kh??ng ???????c ????? tr???ng.'},
      {type: 'pattern', message: 'Vui l??ng nh???p 10 ch??? s???'}
    ],
    customerGender: [
      {type: 'required', message: 'Vui l??ng ch???n gi???i t??nh.'}
    ],
    customerPassport: [
      {type: 'required', message: 'Passport kh??ng ???????c ????? tr???ng.'},
      {type: 'pattern',message: 'Vui l??ng nh???p 9 ho???c 12 ch??? s???'}
    ],
  }

  ngOnInit(): void {

  }
  validate(): void {


  };

  compareFn(c1: any, c2: any): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  };


  editCustomer(){
    this.customer = this.customerForm.value;
    this.customerService.updateCustomer(this.idCustomer,this.customer).subscribe(data=>{
        console.log(data);
        // @ts-ignore
        if(data.status == false){
          // @ts-ignore
          this.msgEmail = data.msgEmail;
          // @ts-ignore
          this.msgPassport = data.msgPassport;
          // @ts-ignore
          this.msgDate = data.msgDate;
          this.toast.error("Vui l??ng ki???m tra l???i d??? li???u nh???p v??o!!!C??c tr?????ng * kh??ng ???????c ????? tr???ng ho???c ch??a ????ng ?????nh d???ng!!!","Th??ng b??o");
        }else {
          this.toast.success("S???a th??nh c??ng","Th??ng b??o");
          this.router.navigateByUrl("/management/customer")
        }
    },
      error => {
     this.toast.error("C??c tr?????ng ph???i ????ng ?????nh d???ng.","Th??ng b??o")
      })
  }

  validAge(control: AbstractControl) {
    const date = control.value;
    const today = new Date();
    const birthDate = new Date(date);
    let age = today.getFullYear() - birthDate.getFullYear();
    const m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
      age--;
    }
    if (age < 18) {
      return {'validAge': true};
    } else {
      return null;
    }
  }
}
