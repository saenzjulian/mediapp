import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ], 
  exports: [ 
    MatTableModule,
    MatIconModule, 
    MatButtonModule
  ]
})
export class MaterialModule { }
