import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ApiService } from './services/api.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  template: '<h1>Cool Kan</h1><p>{{ message }}</p>',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  message = '';

  constructor(private apiService: ApiService) {}

  ngOnInit() {
      this.apiService.testBackendConnection().subscribe(
        response => {
          this.message = response;
          console.log('Backend connection successful:', response);
        },
        error => {
          this.message = 'Error connecting to backend';
          console.error('Backend connection failed:', error);
        }
      );
    }
}
