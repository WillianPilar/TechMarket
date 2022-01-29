import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EstoqueRoutingModule } from './estoque-routing.module';
import { EstoqueListComponent } from './estoque-list/estoque-list.component';
import { FormsModule } from '@angular/forms';
import { EstoqueFormComponent } from './estoque-form/estoque-form.component';


@NgModule({
  declarations: [EstoqueListComponent, EstoqueFormComponent],
  imports: [
    CommonModule,
    EstoqueRoutingModule,
    FormsModule
  ], exports: [
    EstoqueListComponent,
    EstoqueFormComponent
  ]
})
export class EstoqueModule { }
