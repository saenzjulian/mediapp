import { Component, OnInit } from '@angular/core';
import { Menu } from 'src/app/_models/menu';
import { LoginService } from 'src/app/_services/login.service';
import { MenuService } from 'src/app/_services/menu.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {

  menus: Menu[];

  constructor(
    private menuService: MenuService,
    public loginService: LoginService
  ) { }

  ngOnInit(): void {
    this.menuService.getMenuCambio().subscribe(data => {
      this.menus = data;
    });
  }

}
