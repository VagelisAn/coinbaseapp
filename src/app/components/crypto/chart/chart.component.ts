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

  selectedCryptos: { [key: string]: boolean } = {};
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

  onCheckboxChange() {
    const selectedCount = Object.keys(this.selectedCryptos).filter(
      (key) => this.selectedCryptos[key]
    ).length;

    if (selectedCount > 8) {
      const lastSelectedKey = Object.keys(this.selectedCryptos).find(
        (key) => this.selectedCryptos[key]
      );
      console.log(Object.keys(this.selectedCryptos))
      console.log(Object.keys(this.selectedCryptos))
      if (lastSelectedKey) {
        this.selectedCryptos[lastSelectedKey] = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'You can only select up to 8 cryptocurrencies!!!!!!!!!!!!!',
        });
      }
    }
  }

  onChartTypeChange() {
    this.updateChart();
  }

  updateChart() {
    if (Object.keys(this.selectedCryptos).length === 0) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Please select at least one cryptocurrency!',
      });
      return;
    }

    const keysArray = Object.keys(this.selectedCryptos);
    this.showCryptos = [];

    keysArray.forEach((crypt) => {
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

    // this.chartOptions = {
    //   tooltip: {
    //     trigger: 'item',
    //     formatter: (params: any) => {
    //       return `<b>${params.name}</b><br/>
    //                 Symbol: ${params.data.symbol.toUpperCase()}<br/>
    //                 Market Cap: $${params.data.market_cap.toLocaleString()}<br/>
    //                 Current Price: $${params.data.current_price.toLocaleString()}`;
    //     },
    //   },
    //   xAxis: {
    //     type: 'category',
    //     data: this.showCryptos.map((c) => c.name),
    //     axisLabel: { rotate: 45 },
    //   },
    //   yAxis: {
    //     type: 'value',
    //     name: 'Market Cap ($)',
    //     axisLabel: {
    //       formatter: (value: number) => `$${(value / 1e9).toFixed(2)}B`,
    //     },
    //   },
    //   series: [
    //     {
    //       name: 'Market Cap',
    //       type: 'bar',
    //       data: this.showCryptos.map((c) => ({
    //         value: c.market_cap,
    //         name: c.name,
    //         symbol: c.symbol,
    //         market_cap: c.market_cap,
    //         current_price: c.current_price,
    //       })),
    //       itemStyle: {
    //         color: (params) => {
    //           const value = params.value as number;
    //           if (value < 3835940174) {
    //             return 'red';
    //           } else if (value < 5912754198) {
    //             return 'yellow';
    //           } else {
    //             return 'green';
    //           }
    //         },
    //       },
    //     },
    //   ],
    // };
  }

  // initDispatcher() {
  //     this.store.dispatch(loadAllCryptos());
  //   }

  initSelectors() {
    this.subscriptionCryptos = this.store
      .select(selectCryptos)
      .subscribe((data) => {
        this.cryptos = [...data];

        this.messageService.add({
          severity: 'success',
          summary: 'Success Message',
          detail: 'Your data was loaded successfully.',
        });
      });

    this.subscriptionError = this.store.select(selectCryptoError).subscribe({
      next: (message) => {
        if (message) {
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
