import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [CommonModule, TableModule, FormsModule],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent implements OnInit {
  cryptos!: Crypto[];
  isLoading = true;
  constructor() {}

  ngOnInit() {
   
  }

}
