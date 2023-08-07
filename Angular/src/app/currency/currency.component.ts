import { Component } from '@angular/core';
import { CurrencyService } from '../_services/currency.service';
import { catchError } from 'rxjs/operators';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Currency } from '../_models/currency.model';

@Component({
  selector: 'app-currency',
  templateUrl: './currency.component.html',
  styleUrls: ['./currency.component.scss']
})
export class CurrencyComponent {

  allRecords: Currency[] | null = null;

  currencyValue = '';
  nameValue = '';
  currencyInvalid = false;
  nameInvalid = false;
  currencyValid = false;

  borderState = '';
  request: any;
  value: any;
  date: string | number | Date | undefined;

  showError = false;

  constructor(private currencyService: CurrencyService) {}

  onSubmit(): void {

    if (!this.currencyValue || !this.nameValue) {
      this.currencyInvalid = !this.currencyValue;
      this.nameInvalid = !this.nameValue;
      return;
    }

    this.currencyInvalid = false;
    this.nameInvalid = false;

    this.currencyService.getActualCurrencyValue({ currency: this.currencyValue, name: this.nameValue }).pipe(
      catchError(error => {
        console.error('Error in POST request123:', error);
        this.currencyInvalid = true;
        this.showError = true;
        return [];
      })
    )
    .subscribe(response => {
      this.request = response;
      this.borderState = response ? 'success' : 'error';
        setTimeout(() => {
          this.borderState = '';
        }, 2000);
      this.currencyValid = true;
    });
  }


  getAllRecords(): void{
    this.currencyService.getAllRecords().subscribe(
      (records) => {
        this.allRecords = records;
      },
      (
        e => {console.error('Error 123', e);
      })
    )
    this.showError = false;
  }
}
