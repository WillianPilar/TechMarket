import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EstoqueService } from 'src/app/estoque.service';
import { ProdutoNoEstoqueResponse } from '../produto-no-estoque-response';

@Component({
  selector: 'app-estoque-list',
  templateUrl: './estoque-list.component.html',
  styleUrls: ['./estoque-list.component.css']
})
export class EstoqueListComponent implements OnInit {

  produtos : ProdutoNoEstoqueResponse[] = [];
  produtoSelecionado : ProdutoNoEstoqueResponse = new ProdutoNoEstoqueResponse();
  deletadoSucesso : String | undefined;
  deletadoErro : String | undefined;

  constructor(
    private service : EstoqueService,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.getAllProdutosNoEstoque();
  }

  getAllProdutosNoEstoque(){
    this.service.getAllEstoque()
      .subscribe( response => {
        this.produtos = response
      });
  }

  novoCadastro(){
    this.router.navigate(["/estoque-form"]);
  }

  preparaDelecao( produto : ProdutoNoEstoqueResponse){
    this.produtoSelecionado = produto;
  }

  deletarProduto(){

    this.service.deletarProduto(this.produtoSelecionado)
      .subscribe( response => {
        this.deletadoSucesso = "Código de barras deletado com sucesso";
        this.deletadoErro = undefined;
        this.getAllProdutosNoEstoque();
      }, errorResponse => {
        this.deletadoErro = "Erro ao deletar o código de barras";
        this.deletadoSucesso = undefined;
      });
  }

}
