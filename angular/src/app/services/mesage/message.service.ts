import { Injectable } from '@angular/core';
import { Message } from 'primeng/api';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private messageSubject = new Subject<Message[]>();
  messages$ = this.messageSubject.asObservable();

  showError(message: string) {
    this.messageSubject.next([{ severity: 'error', summary: 'Error', detail: message }]);
  }

  showSuccess(message: string) {
    this.messageSubject.next([{ severity: 'success', summary: 'Success', detail: message }]);
  }

  clear() {
    this.messageSubject.next([]);
  }
}