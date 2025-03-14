import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CryptoService {
  private apiUrl = 'http://localhost:8081/api/crypto/coin/gecko';

  constructor(private http: HttpClient) { }

  getAllCryptos(selectedCurrency: string, selectedSortOrder: string): Observable<Crypto[]> {
    return this.http
      .get<Crypto[]>(`${this.apiUrl}?vs_currency=${selectedCurrency}&order=${selectedSortOrder}`)
      .pipe(
        catchError(this.handleError) // Handle errors
      );
  }

  private handleError(error: HttpErrorResponse) {
    console.error('API Error:', error);
    return throwError(() => new Error('Error fetching data. Please try again later.'));
  }
}
