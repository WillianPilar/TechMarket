import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TemplateModule } from './template/template.module';
import { HomeComponent } from './home/home.component';
import { FuncionariosModule } from './funcionarios/funcionarios.module';
import { HttpClientModule } from '@angular/common/http';
import { BarcodeModule } from './barcode/barcode.module';
import { EstoqueModule } from './estoque/estoque.module';
import { CaixaModule } from './caixa/caixa.module';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    TemplateModule,
    FuncionariosModule,
    BarcodeModule,
    EstoqueModule,
    CaixaModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
