import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CaixaRoutingModule } from './caixa-routing.module';
import { CaixaListComponent } from './caixa-list/caixa-list.component';
import { CaixaFormComponent } from './caixa-form/caixa-form.component';
import { FormsModule } from '@angular/forms';
import { CaixaTelaComponent } from './caixa-tela/caixa-tela.component';


@NgModule({
  declarations: [CaixaListComponent, CaixaFormComponent, CaixaTelaComponent],
  imports: [
    CommonModule,
    CaixaRoutingModule,
    FormsModule
  ], exports: [
    CaixaFormComponent,
    CaixaListComponent,
    CaixaTelaComponent
  ]
})
export class CaixaModule { }
