import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Currency } from '../_models/currency.model';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  private api = 'http://localhost:8080/api/currencies';

  constructor(private httpClient: HttpClient) { }

  getActualCurrencyValue(request: Currency): Observable<Currency>{
    return this.httpClient.post<Currency>(`${this.api}/get-current-currency-value-command`, request)
      .pipe(
          catchError(error => { 
            console.error('Error in POST request123122da:', error);
            return throwError(new Error('Niewłaściwy kod'));
        }
      )
    )
  }

  getAllRecords(): Observable<Currency[]> {
    return this.httpClient.get<Currency[]>(`${this.api}/requests`);
  }

}
