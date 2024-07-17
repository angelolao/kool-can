import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  testBackendConnection(): Observable<string> {
      return this.http.get(`${this.apiUrl}/test`, { responseType: 'text' });
    }

  testCors(): Observable<string> {
    return this.http.get(`${this.apiUrl}/test`, { responseType: 'text' })
      .pipe(
        tap(response => console.log('Response:', response)),
        catchError(error => {
          console.error('Error:', error);
          return throwError(() => new Error('Something went wrong'));
        })
      );
  }
}
