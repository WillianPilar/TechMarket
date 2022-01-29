import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FuncionariosFormComponent } from './funcionarios-form/funcionarios-form.component';
import { FuncionariosListComponent } from './funcionarios-list/funcionarios-list.component';

const routes: Routes = [
  { path: 'funcionarios-form', component : FuncionariosFormComponent },
  { path: 'funcionarios-form/:id', component : FuncionariosFormComponent },
  { path: 'funcionarios-list', component : FuncionariosListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FuncionariosRoutingModule { }