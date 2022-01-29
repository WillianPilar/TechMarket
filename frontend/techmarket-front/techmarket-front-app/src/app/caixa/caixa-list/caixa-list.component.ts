import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CaixasService } from 'src/app/caixas.service';
import { CaixaResponse } from '../caixa-response';

@Component({
  selector: 'app-caixa-list',
  templateUrl: './caixa-list.component.html',
  styleUrls: ['./caixa-list.component.css']
})
export class CaixaListComponent implements OnInit {

  caixas: CaixaResponse[] = [];
  caixa: CaixaResponse = new CaixaResponse();
  caixaSelecionado : CaixaResponse = new CaixaResponse();

  constructor(
    private service: CaixasService,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.getAllCaixas();
  }


  novoCadastro() {
    this.router.navigate(['/caixa-form']);
  }

  getAllCaixas() {
    this.service.getAllCaixas()
      .subscribe( response => {
        this.caixas = response;
        console.log(this.caixas);
      });
  }

  preparaDelecao(caixa : CaixaResponse){
    this.caixaSelecionado = caixa;
  }

  deletarCaixa(){
    console.log(this.caixaSelecionado);
    this.service.deletarCaixa(this.caixaSelecionado.numeroDoCaixa)
      .subscribe(response =>{
        console.log(response);
        this.getAllCaixas();
      });

  }
  
}

