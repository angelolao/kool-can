import { provideHttpClient } from '@angular/common/http';
import {
  HttpTestingController,
  provideHttpClientTesting,
} from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { AuthService } from './auth.service';

describe('AuthService', () => {
  let service: AuthService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthService, provideHttpClient(), provideHttpClientTesting()],
    });
    service = TestBed.inject(AuthService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should update currentUserSubject on successful login check', () => {
    const mockUser = { name: 'Test User' };

    service.checkLoginStatus().subscribe((user) => {
      expect(user).toEqual(mockUser);
      expect(service.currentUserValue).toEqual(mockUser);
    });

    const req = httpMock.expectOne('http://localhost:8080/user');
    expect(req.request.method).toBe('GET');
    req.flush(mockUser);
  });

  it('should clear currentUserSubject on logout', () => {
    service.logout();

    const req = httpMock.expectOne('http://localhost:8080/logout');
    expect(req.request.method).toBe('POST');
    req.flush({});

    expect(service.currentUserValue).toBeNull();
  });
});
