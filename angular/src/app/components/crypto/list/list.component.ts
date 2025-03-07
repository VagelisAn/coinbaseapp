import { Component, OnDestroy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { MessageService } from 'primeng/api';
import { Observable, Subscription } from 'rxjs';
import { Crypto } from 'src/app/models/crypto.model';
import { loadAllCryptos, loadCryptos } from 'src/app/store/crypto.actions';
import {
  selectCryptoError,
  selectCryptos,
} from 'src/app/store/crypto.selectors';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrl: './list.component.css',
})
export class ListComponent implements OnInit, OnDestroy {
  cryptos!: Crypto[];
  isLoading = true;
  subscriptionCryptos!: Subscription;
  subscriptionError!: Subscription;

  constructor(private store: Store, private messageService: MessageService) {}

  ngOnInit() {
    // this.initDispatcher();
    this.initSelectors();
  }

  ngOnDestroy(): void {
    if (this.subscriptionCryptos) {
      this.subscriptionCryptos.unsubscribe();
    }
    if (this.subscriptionError) {
      this.subscriptionError.unsubscribe();
    }
  }

  // initDispatcher() {
  //   this.store.dispatch(loadAllCryptos());
  // }

  initSelectors() {
    this.subscriptionCryptos = this.store.select(selectCryptos).subscribe({
      next: (crypto) => {
        if (crypto) {
          this.messageService.add({
            severity: 'success',
            summary: 'Success Message',
            detail: 'Your data was loaded successfully.',
            life: 3000,
          });
          this.cryptos = [...crypto];
          this.isLoading = false;
        }
      },
    });
    // can use instead of manual handle observable can use the follow to make auto handle
    // this.cryptos$ = this.store.select('cryptos').pipe(
    //   map((data) => [...data]) // Create a new array for sorting
    // );

    this.subscriptionError = this.store.select(selectCryptoError).subscribe({
      next: (message) => {
        if (message) {
          this.isLoading = false;
          this.messageService.add({
            severity: 'error',
            summary: 'Error Message',
            detail: 'Something went wrong. Please try again.',
          });
        }
      },
    });
  }
}
