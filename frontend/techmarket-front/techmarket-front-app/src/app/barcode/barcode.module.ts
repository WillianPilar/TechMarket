import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BarcodeRoutingModule } from './barcode-routing.module';
import { FormsModule } from '@angular/forms';
import { BarcodeFormComponent } from './barcode-form/barcode-form.component';
import { BarcodeListComponent } from './barcode-list/barcode-list.component';


@NgModule({
  declarations: [BarcodeFormComponent, BarcodeListComponent],
  imports: [
    CommonModule,
    BarcodeRoutingModule,
    FormsModule
  ],exports: [
    BarcodeFormComponent,
    BarcodeListComponent
  ]
})
export class BarcodeModule { }
