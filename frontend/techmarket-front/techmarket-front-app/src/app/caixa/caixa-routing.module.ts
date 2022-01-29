import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CaixaFormComponent } from './caixa-form/caixa-form.component';
import { CaixaListComponent } from './caixa-list/caixa-list.component';
import { CaixaTelaComponent } from './caixa-tela/caixa-tela.component';

const routes: Routes = [
  { path: 'caixa-list', component : CaixaListComponent },
  { path: 'caixa-form', component: CaixaFormComponent },
  { path: 'caixa-form/:caixaNumber', component : CaixaFormComponent },
  { path: 'caixa-tela', component : CaixaTelaComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CaixaRoutingModule { }
