import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FuncionariosFormComponent } from './funcionarios-form/funcionarios-form.component';
import { FuncionariosRoutingModule } from './funcionarios-routing.module';
import { FormsModule } from '@angular/forms';
import { FuncionariosListComponent } from './funcionarios-list/funcionarios-list.component';



@NgModule({
  declarations: [FuncionariosFormComponent, FuncionariosListComponent],
  imports: [
    CommonModule,
    FuncionariosRoutingModule,
    FormsModule
  ],
  exports: [
    FuncionariosFormComponent,
    FuncionariosListComponent
  ]
})
export class FuncionariosModule { }
