import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CryptoService {
  private apiUrl = 'https://api.coingecko.com/api/v3/coins/markets';

  constructor(private http: HttpClient) {}

  getCryptos(page: number = 1, perPage: number = 10): Observable<Crypto[]> {
    return this.http
      .get<Crypto[]>(`${this.apiUrl}?vs_currency=usd&order=market_cap_desc&per_page=${perPage}&page=${page}`)
      .pipe(
        catchError(this.handleError) // Handle errors
      );
  }

  getAllCryptos(): Observable<Crypto[]> {
    return this.http
      .get<Crypto[]>(`${this.apiUrl}?vs_currency=usd&order=market_`)
      .pipe(
        catchError(this.handleError) // Handle errors
      );
  }

  private handleError(error: HttpErrorResponse) {
    console.error('API Error:', error);
    return throwError(() => new Error('Error fetching data. Please try again later.'));
  }
}
