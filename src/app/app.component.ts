import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  isExpanding = false;

  constructor() {}

  ngOnInit(): void { }

  toggleSideBar() {
    this.isExpanding = !this.isExpanding;
  }

}