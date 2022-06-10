
import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {TokenStorageService} from '../../user/user-service/token-storage.service';
import {LoginRegisterComponent} from '../../user/user-component/login-register/login-register.component';

import '../../../assets/header';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isDropDown = false;
  isLogin = false;
  accountName = 'Việt Nam Vô Địch';
  ismod: boolean;
  @Input()
  isAdmin =  false;
  isModerator = false;
  private roles: string[];
  isLoggedIn = false;
  username: string;
  private showAdminBoard: boolean;
  private showModeratorBoard: boolean;
  private showUserBoard: boolean;

  constructor(private router: Router,
              private route: ActivatedRoute, private dialog: MatDialog,
              private tokenStorageService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');
      this.showUserBoard = this.roles.includes('ROLE_USER');

      if (this.showAdminBoard || this.showModeratorBoard) {
        this.username = user.employee.employeeName;
      } else if (this.showUserBoard) {
        this.username = user.customer.customerName;
      }
      // @ts-ignore
      this.isModerator = this.roles == 'ROLE_MODERATOR';
      // @ts-ignore
      // tslint:disable-next-line:triple-equals
      this.ismod = (this.roles == 'ROLE_MODERATOR' || this.roles == 'ROLE_ADMIN')
      if(this.ismod){

        this.isAdmin = true;
        console.log(this.isAdmin)
      }
    }
  }

  getDropDown() {
    this.isDropDown = !this.isDropDown;
  }

  logout() {
    this.tokenStorageService.signOut();
    this.router.navigateByUrl('').then(() => {window.location.reload()})
  }

  openDialogLogin() {
    const dialogRef = this.dialog.open(LoginRegisterComponent, {});
    dialogRef.afterClosed().subscribe(() => {

    });
  }



}
