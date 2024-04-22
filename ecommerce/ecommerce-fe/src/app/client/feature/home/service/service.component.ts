import { Component } from '@angular/core';
import { serviceData } from './service.data';
@Component({
  selector: 'app-home-service',
  templateUrl: './service.component.html',
  styleUrls: ['./service.component.scss']
})
export class ServiceComponent {
  serviceImgs: any;
  ngOnInit() {

    this.serviceImgs = serviceData;
  }
}
