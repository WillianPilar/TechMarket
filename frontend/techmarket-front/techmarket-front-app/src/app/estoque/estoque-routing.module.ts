import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EstoqueFormComponent } from './estoque-form/estoque-form.component';
import { EstoqueListComponent } from './estoque-list/estoque-list.component';

const routes: Routes = [
  { path: 'estoque-list', component : EstoqueListComponent },
  { path: 'estoque-form', component : EstoqueFormComponent },
  { path : "estoque-form/:productId", component : EstoqueFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EstoqueRoutingModule { }
