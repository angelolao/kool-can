import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router, RouterModule } from '@angular/router';
import { filter } from 'rxjs/operators';
import { BackgroundComponent } from './background.component';
import { HeaderComponent } from './components/header/header.component';
import { UserMenuComponent } from './components/userMenu/user-menu.component';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    BackgroundComponent,
    HeaderComponent,
    UserMenuComponent,
  ],
  template: `
    <app-background></app-background>
    <app-header></app-header>
    <app-user-menu *ngIf="showUserMenu"></app-user-menu>
    <router-outlet></router-outlet>
    <div *ngIf="isLoading">Loading...</div>
  `,
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  currentUser: any;
  isLoading = true;
  showUserMenu = false;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.authService.currentUser.subscribe((user) => {
      this.currentUser = user;
      this.updateUserMenuVisibility();

      if (!user && this.router.url !== '/login') {
        this.router.navigate(['/login']);
      }
    });

    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.updateUserMenuVisibility();
      this.checkLoginStatus();
    });

    this.checkLoginStatus();
  }

  checkLoginStatus() {
    this.isLoading = true;
    this.authService.checkLoginStatus().subscribe(
      (user) => {
        this.isLoading = false;
        if (!user && this.router.url !== '/login') {
          this.router.navigate(['/login']);
        }
      },
      (error) => {
        console.error('Error checking login status', error);
        this.isLoading = false;
        this.router.navigate(['/login']);
      }
    );
  }

  updateUserMenuVisibility() {
    this.showUserMenu = this.currentUser && this.router.url !== '/login';
  }
}