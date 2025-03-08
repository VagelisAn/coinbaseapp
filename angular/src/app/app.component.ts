import { Component, OnInit } from '@angular/core';
import { Message } from 'primeng/api';
import { MessageService } from './services/mesage/message.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  isExpanding = false;
  messages: Message[] = [];

  constructor(private messageService: MessageService) {}

  ngOnInit(): void {
    this.messageService.messages$.subscribe((msgs) => {
      this.messages = msgs;
    });
  }

  toggleSideBar() {
    this.isExpanding = !this.isExpanding;
  }

}