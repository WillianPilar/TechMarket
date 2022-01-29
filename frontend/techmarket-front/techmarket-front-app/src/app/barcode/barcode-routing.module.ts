import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BarcodeFormComponent } from './barcode-form/barcode-form.component';
import { BarcodeListComponent } from './barcode-list/barcode-list.component';

const routes: Routes = [
  { path : "barcode-form", component : BarcodeFormComponent },
  { path : "barcode-form/:barcode", component : BarcodeFormComponent },
  { path : "barcode-list", component : BarcodeListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BarcodeRoutingModule { }
