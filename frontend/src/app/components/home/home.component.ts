import { CommonModule, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { LoginComponent } from '../login/login.component';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [LoginComponent, CommonModule, NgIf],
  template: `
    <div class="content">
      <ng-container *ngIf="!isLoading">
        <app-login *ngIf="!isLoggedIn"></app-login>
      </ng-container>
    </div>
  `,
  styles: [
    `
      .content {
        padding-top: 80px;
      }
    `,
  ],
})
export class HomeComponent {
  isLoading = true;
  isLoggedIn = false;
  currentUser: any;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.checkLoginStatus();
  }

  checkLoginStatus() {
    this.authService.checkLoginStatus().subscribe(
      (user) => {
        this.currentUser = user;
        this.isLoggedIn = !!user;
        this.isLoading = false;
        if (!user) {
          this.router.navigate(['/login']);
        }
      },
      (error) => {
        console.error('Error checking login status', error);
        this.isLoading = false;
      }
    );
  }
}
