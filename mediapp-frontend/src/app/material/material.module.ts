import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatDividerModule } from '@angular/material/divider';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar'; 
import { MatSortModule } from '@angular/material/sort';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSnackBarModule } from '@angular/material/snack-bar';




@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ], 
  exports: [ 
    MatTableModule,
    MatIconModule, 
    MatButtonModule,
    MatSidenavModule,
    MatDividerModule,
    MatMenuModule,
    MatToolbarModule, 
    MatSortModule, 
    MatInputModule, 
    MatFormFieldModule, 
    MatPaginatorModule,
    MatSnackBarModule
  ]
})
export class MaterialModule { }
