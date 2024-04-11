import { Component, HostListener, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { AuthService } from 'src/app/store/Auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isLoggedIn = false;
  isMenuOpen = false;
  isLargeScreen = true;
  userId: number | undefined;
  token: string | null = localStorage.getItem('token');
  constructor(
    private store: Store,
    private authServie: AuthService,
  ) { }

  ngOnInit() {
    const token = localStorage.getItem('userToken');
    console.log(token);
    if (token != null) {
      this.isLoggedIn = true;
    }

  }

  // Hàm này được gọi mỗi khi kích thước của cửa sổ thay đổi
  @HostListener('window:resize', ['$event'])
  onResize() {
    this.checkScreenSize();
  }

  // Kiểm tra kích thước của màn hình và cập nhật biến isLargeScreen
  checkScreenSize() {
    this.isLargeScreen = window.innerWidth > 768; // Ví dụ, bạn có thể sử dụng 768px là ngưỡng cho màn hình lớn
  }
  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen; // Toggle menu state
  }

  getItemLength() {

  }

  logout() {
    this.isLoggedIn = false;
    this.authServie.logout();
  }

}
