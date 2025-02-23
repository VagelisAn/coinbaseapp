import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Store, select } from '@ngrx/store';
import { Observable, tap } from 'rxjs';
import { Crypto } from 'src/app/models/crypto.model';
import {
  loadAllCryptos,
  setPage,
  setSearchTerm,
} from 'src/app/store/crypto.actions';
import {
  selectCryptoLoading,
  selectCurrentPage,
  selectFilteredCryptos,
  selectItemsPerPage,
  selectTotalCryptos,
} from 'src/app/store/crypto.selectors';

@Component({
  selector: 'app-material',
  templateUrl: './material.component.html',
  styleUrl: './material.component.css',
})
export class MaterialComponent implements OnInit, AfterViewInit {
  
 
  @ViewChild(MatSort) sort!: MatSort;
  
  displayedColumns: string[] = [
    'name',
    'symbol',
    'current_price',
    'market_cap',
  ];
  dataSource = new MatTableDataSource<Crypto>([]);

  cryptos$!: Observable<Crypto[]>;
  isLoading$!: Observable<boolean>;
  currentPage$!: Observable<number>;
  itemsPerPage$!: Observable<number>;
  totalCryptos$!: Observable<number>;
  totalCrypto!: number;


  constructor(private store: Store) {}

  ngOnInit() {
    this.initSelectors();
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  initSelectors() {
    this.cryptos$ = this.store.pipe(select(selectFilteredCryptos));
    this.isLoading$ = this.store.pipe(select(selectCryptoLoading));
    this.currentPage$ = this.store.pipe(select(selectCurrentPage));
    this.itemsPerPage$ = this.store.pipe(select(selectItemsPerPage));
    this.totalCryptos$ = this.store.pipe(select(selectTotalCryptos));

    this.totalCryptos$.subscribe((cryptos) => {
      console.log(cryptos)
      this.totalCrypto = cryptos;
    });
  
    this.cryptos$.subscribe((cryptos) => {
      this.dataSource.data = cryptos;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.store.dispatch(
      setSearchTerm({ searchTerm: filterValue.trim().toLowerCase() })
    );
  }

  sortData(column: string) {}

  changePage(pageEvent: any) {
    this.store.dispatch(setPage({ page: pageEvent.pageIndex + 1}));
  }
}
