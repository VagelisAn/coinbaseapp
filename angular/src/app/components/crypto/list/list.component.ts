import { CommonModule, NgIf } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { Subscription } from 'rxjs';
import { CryptoService } from '../../../services/crypto/crypto.service';

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [CommonModule, TableModule, FormsModule, NgIf],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent implements OnInit {
  cryptos: Crypto[] = [];
  isLoading = true;
 
  private subscription!: Subscription;
  
  constructor(private cryptoService: CryptoService) {}

  ngOnInit(): void {
    this.subscription = this.cryptoService.getAllCryptos().subscribe({
      next: (data: any[]) => {
        console.log(data)
        this.isLoading =  false;
        this.cryptos = data; // ✅ Assign the data to the variable
      },
      error: (err) => console.error('Error fetching cryptos:', err),
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe(); // ✅ Prevent memory leaks
  }
}
