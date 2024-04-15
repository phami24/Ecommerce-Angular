import { Component } from '@angular/core';
import { menJackets } from './men';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  menJackets: any;
  ngOnInit() {

    this.menJackets = menJackets;
  }
}
