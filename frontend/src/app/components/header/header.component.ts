import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: true,
  template: `
    <header>
      <img src="assets/images/logos/logo-white-trsp.png" alt="Kool-Can Logo" class="logo">
    </header>
  `,
  styles: [`
    .logo {
      position: fixed;
      top: 10px;
      left: 20px;
      width: 100px;
      height: auto;
      z-index: 1000;
    }
  `]
})

export class HeaderComponent {}