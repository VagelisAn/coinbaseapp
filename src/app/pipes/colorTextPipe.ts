import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Pipe({
    name: 'colorText'
  })
  export class ColorTextPipe implements PipeTransform {
  
    constructor(private sanitizer: DomSanitizer) {}
  
    transform(value: number): SafeHtml {
      let color = 'black'; // Default color
  
      if (value > 1000) {
        color = 'green';
      } else if (value < 100) {
        color = 'red';
      } else {
        color = 'blue';
      }
  
      // Format the number as currency and apply color
      const formattedValue = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD'
      }).format(value);
  
      return this.sanitizer.bypassSecurityTrustHtml(`<span style="color: ${color};">${formattedValue}</span>`);
    }
  }