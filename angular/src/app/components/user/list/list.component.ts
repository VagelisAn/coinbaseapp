import { CommonModule, NgIf } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { Subscription } from 'rxjs';
import { CryptoService } from '../../../services/crypto/crypto.service';
import { DropdownModule } from 'primeng/dropdown';
import { CardModule } from 'primeng/card';


@Component({
  selector: 'app-list',
  standalone: true,
  imports: [CommonModule, TableModule, FormsModule, NgIf, DropdownModule, CardModule],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent implements OnInit {
  cryptos: Crypto[] = [];

  currencies = [
    { label: 'USD', value: 'usd' },
    { label: 'Japanese Yen', value: 'jpy' },
    { label: 'Euro', value: 'eur' },
    { label: 'Bitcoin', value: 'btc' },
    { label: 'Ether', value: 'eth' },
    { label: 'Binance Coin', value: 'bnb' }
  ];

  sortOrders = [
    { label: 'Market Cap Descending', value: 'market_cap_desc' },
    { label: 'Market Cap Ascending', value: 'market_cap_asc' }
  ];

  // Default selected values
  selectedCurrency: string = 'usd';
  selectedSortOrder: string = 'market_cap_desc';

  isLoading = true;

  private subscription!: Subscription;

  constructor(private cryptoService: CryptoService) { }

  ngOnInit(): void {
    // add a store for crypto 
    this.fetchData();
  }

  onCurrencyChange(event: any) {
    console.log('Selected Currency:', event.value);
    this.selectedCurrency = event.value;
    this.fetchData();
  }

  onSortOrderChange(event: any) {
    console.log('Selected Sort Order:', event.value);
    this.selectedSortOrder = event.value;
    this.fetchData();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe(); // Prevent memory leaks
  }

  fetchData() {
    this.subscription = this.cryptoService.getAllCryptos(this.selectedCurrency, this.selectedSortOrder).subscribe({
      next: (data: any[]) => {
        console.log(data)
        this.isLoading = false;
        this.cryptos = data;
      },
      error: (err) => console.error('Error fetching cryptos:', err),
    });
  }
}
