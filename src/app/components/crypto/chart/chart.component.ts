import { Component, OnDestroy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { EChartsOption } from 'echarts';
import { MessageService } from 'primeng/api';
import { Subscription } from 'rxjs';
import { Crypto } from 'src/app/models/crypto.model';
import {
  selectCryptoError,
  selectCryptos,
} from 'src/app/store/crypto.selectors';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrl: './chart.component.css',
})
export class ChartComponent implements OnInit, OnDestroy {
  cryptos!: Crypto[];
  showCryptos!: Crypto[];

  // selectedCryptos: { [key: string]: boolean } = {};
  selectedCryptos: Set<string> = new Set();
  selectedChartType: string = 'line';

  subscriptionCryptos!: Subscription;
  subscriptionError!: Subscription;

  chartOptions: EChartsOption = {};

  chartTypes = [
    { label: 'Line Chart', value: 'line' },
    { label: 'Bar Chart', value: 'bar' },
    { label: 'Pie Chart', value: 'pie' }
  ];

  constructor(private store: Store, private messageService: MessageService) {}

  ngOnInit(): void {
    this.initSelectors();
    // this.initDispatcher();
  }

  ngOnDestroy(): void {
    if (this.subscriptionCryptos) {
      this.subscriptionCryptos.unsubscribe();
    }
    if (this.subscriptionError) {
      this.subscriptionError.unsubscribe();
    }
  }

  isSelected(cryptoId: string): boolean {
    return this.selectedCryptos.has(cryptoId);
  }

  onCheckboxChange(event: any, cryptoId: string) {
    if (event.checked) {
      if (this.selectedCryptos.size >= 8) {
          return;
      } else if (this.selectedCryptos.size == 7) {
        this.showMessage('warn','Selection Limit','You can select up to 8 cryptocurrencies. Last selection!');
        this.selectedCryptos.add(cryptoId);
      }
      else {
        this.selectedCryptos.add(cryptoId);
      }
    } else {
      this.selectedCryptos.delete(cryptoId);
    }

  
  }

  onChartTypeChange() {
    this.updateChart();
  }

  updateChart() {

    if (this.selectedCryptos.size === 0) {
      this.showMessage('error', 'Error', 'Please select at least one cryptocurrency!');
      return;
    }
   
    this.showCryptos = [];

    this.selectedCryptos.forEach((crypt) => {
      const filteredCryptos = this.cryptos.filter((c) => c.id === crypt);
      this.showCryptos.push(...filteredCryptos);
    });
   
    if (this.selectedChartType === 'line') {
      this.chartOptions = {
        tooltip: {  trigger: 'item',
          formatter: (params: any) => {
            return `<b>${params.name}</b><br/>
                      Symbol: ${params.data.symbol.toUpperCase()}<br/>
                      Market Cap: $${params.data.market_cap.toLocaleString()}<br/>
                      Current Price: $${params.data.current_price.toLocaleString()}`;
          }, },
        xAxis: {
          type: 'category',
          data: this.showCryptos.map(c => c.name)
        },
        yAxis: {
          type: 'value',
          name: 'Market Cap ($)',
          axisLabel: {
            formatter: (value: number) => `$${(value / 1e9).toFixed(2)}B`,
          },
        },
        series: [{
          name: 'Market Cap',
          type: 'line',
          smooth: true,
          data: this.showCryptos.map((c) => ({
            value: c.market_cap,
            name: c.name,
            symbol: c.symbol,
            market_cap: c.market_cap,
            current_price: c.current_price,
          })),
          itemStyle: {
            color: (params) => {
              const value = params.value as number;
              if (value < 3835940174) {
                return 'red';
              } else if (value < 5912754198) {
                return 'yellow';
              } else {
                return 'green';
              }
            },
          },
        }]
      };
    } else if (this.selectedChartType === 'bar') {
      this.chartOptions = {
        tooltip: {  trigger: 'item',
          formatter: (params: any) => {
            return `<b>${params.name}</b><br/>
                      Symbol: ${params.data.symbol.toUpperCase()}<br/>
                      Market Cap: $${params.data.market_cap.toLocaleString()}<br/>
                      Current Price: $${params.data.current_price.toLocaleString()}`;
          }, },
        xAxis: {
          type: 'category',
          data: this.showCryptos.map(c => c.name)
        },
        yAxis: {
          type: 'value',
          name: 'Market Cap ($)',
          axisLabel: {
            formatter: (value: number) => `$${(value / 1e9).toFixed(2)}B`,
          },
        },
        series: [{
          name: 'Market Cap',
          type: 'bar',
          data: this.showCryptos.map((c) => ({
            value: c.market_cap,
            name: c.name,
            symbol: c.symbol,
            market_cap: c.market_cap,
            current_price: c.current_price,
          })),
          itemStyle: {
            color: (params) => {
              const value = params.value as number;
              if (value < 3835940174) {
                return 'red';
              } else if (value < 5912754198) {
                return 'yellow';
              } else {
                return 'green';
              }
            },
          },
        }]
      };
    } else if (this.selectedChartType === 'pie') {
      this.chartOptions = {
        tooltip: {  trigger: 'item',
          formatter: (params: any) => {
            return `<b>${params.name}</b><br/>
                      Symbol: ${params.data.symbol.toUpperCase()}<br/>
                      Market Cap: $${params.data.market_cap.toLocaleString()}<br/>
                      Current Price: $${params.data.current_price.toLocaleString()}`;
          },
         },
        series: [{
          name: 'Cryptos',
          type: 'pie',
          radius: '55%',
          data: this.showCryptos.map((c) => ({
            value: c.market_cap,
            name: c.name,
            symbol: c.symbol,
            market_cap: c.market_cap,
            current_price: c.current_price,
          }))
        }]
      };
    }
  }

  // initDispatcher() {
  //     this.store.dispatch(loadAllCryptos());
  //   }

  initSelectors() {
    this.subscriptionCryptos = this.store
      .select(selectCryptos)
      .subscribe((data) => {
        this.cryptos = [...data];
        this.cryptos.sort((a, b) => a.name.localeCompare(b.name));
        this.showMessage('success', 'Success Message' ,'Your data was loaded successfully.');
      });

    this.subscriptionError = this.store.select(selectCryptoError).subscribe({
      next: (message) => {
        if (message) {
          this.showMessage('error', 'Error Message' ,'Something went wrong. Please try again.');
        }
      },
    });
  }

  showMessage(severity: string, summary: string, detail: string) {
    this.messageService.add({
      severity: severity,
      summary: summary,
      detail: detail,
    });
  }
}
