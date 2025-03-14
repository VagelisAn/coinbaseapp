import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { NewsService } from '../../../services/crypto-news/news.service';
import { RouterModule } from '@angular/router';
import { News } from '../../../models/news.model';

@Component({
  selector: 'app-news',
  standalone: true,
  imports: [CommonModule, TableModule, ButtonModule, RouterModule],
  templateUrl: './news.component.html',
  styleUrl: './news.component.css'
})
export class NewsComponent {

  news: News[] = [];
  constructor(private newsService: NewsService) { }

  ngOnInit(): void {
    this.newsService.getAll()
      .subscribe((data: any) => {
        this.news = data.results;
      });
  }
}
