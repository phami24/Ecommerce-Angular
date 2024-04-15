import { Component } from '@angular/core';

@Component({
  selector: 'app-home-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.scss']
})
export class BrandComponent {
  brands = [
    { name: 'Filco', url: 'https://www.phongcachxanh.vn/cdn/shop/files/Filco.png?v=1676446235&width=300' },
    { name: 'Finalmouse', url: 'https://www.phongcachxanh.vn/cdn/shop/files/finalmouse.png?v=1676446235&width=300' },
    { name: 'Fnatic', url: '//www.phongcachxanh.vn/cdn/shop/files/fnatic-logo_d293a4cd-b4a5-431e-9c10-e7f36df77656.png?v=1695963007&width=300' },
    { name: 'Lamzu', url: 'https://www.phongcachxanh.vn/cdn/shop/files/lamzu.png?v=1676446235&width=300' },
    { name: 'Yuki Aim', url: 'https://www.phongcachxanh.vn/cdn/shop/files/yuki-aim.png?v=1696496727&width=300' },
    { name: 'Arbiter Studio', url: 'https://www.phongcachxanh.vn/cdn/shop/files/arbiter-300px.png?v=1706602524&width=300' },
    { name: 'Drunkdeer', url: 'https://www.phongcachxanh.vn/cdn/shop/files/drunkdeer-logo.png?v=1700707395&width=300' },
    { name: 'Gamesense', url: 'https://www.phongcachxanh.vn/cdn/shop/files/gamesense-logo-v2.png?v=1689148529&width=300' },
    { name: 'Corepad Germany', url: 'https://www.phongcachxanh.vn/cdn/shop/files/corepad.png?v=1676446236&width=300' },
    { name: 'Lethal Gaming Gear', url: 'https://www.phongcachxanh.vn/cdn/shop/files/lgg-logo.png?v=1690530199&width=300' },
    { name: 'Pulsar', url: 'https://www.phongcachxanh.vn/cdn/shop/files/pulsar.png?v=1676446235&width=300' },
    { name: 'Deltahub', url: 'https://www.phongcachxanh.vn/cdn/shop/files/deltahub-logo.png?v=1712833451&width=300' },
    { name: 'Cherry Xtrfy', url: 'https://www.phongcachxanh.vn/cdn/shop/files/Xtrfy-logo-homepage.png?v=1691579269&width=300' },
    { name: 'Pwnage', url: 'https://www.phongcachxanh.vn/cdn/shop/files/pwnagesq_Logo_black-300-v2.png?v=1688642887&width=300' },
    { name: 'Datacolor Spyder', url: 'https://www.phongcachxanh.vn/cdn/shop/files/datacolor.png?v=1676446235&width=300' },
    { name: 'Ninjutso', url: 'https://www.phongcachxanh.vn/cdn/shop/files/ninjutso.png?v=1676446235&width=300' },
    { name: 'Skypad', url: 'https://www.phongcachxanh.vn/cdn/shop/files/skypad.png?v=1676446235&width=300' }
  ];
}
