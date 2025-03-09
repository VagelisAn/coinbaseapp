import { CommonModule, NgFor } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgxEchartsModule,  provideEchartsCore} from 'ngx-echarts';
import * as echarts from 'echarts/core';
import { EChartsOption } from 'echarts';
import { TitleComponent, TooltipComponent } from 'echarts/components'; 
import { Crypto } from '../../../models/crypto.model';
import { CryptoService } from '../../../services/crypto/crypto.service';
import { Subscription } from 'rxjs';
import { PdfUtil } from '../../../utils/pdfUtil';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { FormsModule } from '@angular/forms';
import { BarChart, LineChart } from 'echarts/charts';
import { GridComponent } from 'echarts/components';
import { CanvasRenderer } from 'echarts/renderers';


echarts.use([LineChart, TooltipComponent, TitleComponent, CanvasRenderer, GridComponent, BarChart]);
echarts.use([TooltipComponent]);
@Component({
  selector: 'app-chart',
  standalone: true,
  imports: [CommonModule, FormsModule, CheckboxModule, ButtonModule, DropdownModule, NgxEchartsModule, NgFor],
  providers: [
    provideEchartsCore({ echarts }),
  
  ],
  templateUrl: './chart.component.html',
  styleUrl: './chart.component.css'
})
export class ChartComponent implements OnInit, OnDestroy {
  cryptos!: Crypto[];
  showCryptos!: Crypto[];

  selectedCryptos: Set<string> = new Set();
  selectedChartType: string = 'line';

  chartOptions: EChartsOption = {};

  private subscription!: Subscription; 

  chartTypes = [
    { label: 'Pie Chart', value: 'pie' },
    { label: 'Bar Chart', value: 'bar' },
    { label: 'Line Chart', value: 'line' }
  ];

  pdfHeaders = ['Name', 'Symbol', 'Price (USD)', 'Market Cap (USD)'];

  constructor(private cryptoService: CryptoService) {}

  ngOnInit(): void {
    this.subscription = this.cryptoService.getAllCryptos().subscribe({
      next: (data: any[]) => {
        console.log(data)
        this.cryptos = data; // ✅ Assign the data to the variable
      },
      error: (err) => console.error('Error fetching cryptos:', err),
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe(); // ✅ Prevent memory leaks
  }

  isSelected(cryptoId: string): boolean {
    return this.selectedCryptos.has(cryptoId);
  }

  onCheckboxChange(event: any, cryptoId: string) {
    if (event.checked) {
      if (this.selectedCryptos.size >= 8) {
          return;
      } else if (this.selectedCryptos.size == 7) {
        // this.showMessage('warn','Selection Limit','You can select up to 8 cryptocurrencies. Last selection!');
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
      // this.showMessage('error', 'Error', 'Please select at least one cryptocurrency!');
      return;
    }
   
    this.showCryptos = this.exportSelectedCryptos(this.selectedCryptos);
   
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


  exportPdf() {
    const data = this.exportSelectedCryptos(this.selectedCryptos).map(crypto => [
      crypto.name,
      crypto.symbol,
      crypto.current_price.toFixed(2),
      crypto.market_cap.toLocaleString()
    ]);
   PdfUtil.exportToPdf("Exprot data for Crypto", this.pdfHeaders, data, "Export.pdf")
  }


  exportSelectedCryptos(selectedCryptos: Set<string>):Crypto[] {
    this.showCryptos = [];

    this.selectedCryptos.forEach((crypt) => {
      const filteredCryptos = this.cryptos.filter((c) => c.id === crypt);
      this.showCryptos.push(...filteredCryptos);
    });
    return  this.showCryptos;
  }
}
