import { Component, OnInit } from '@angular/core';
import { Barcode } from 'src/app/barcode/barcode';
import { CaixaTelaService } from 'src/app/caixa-tela.service';
import { ProdutoCompra } from './produto-compra';

@Component({
  selector: 'app-caixa-tela',
  templateUrl: './caixa-tela.component.html',
  styleUrls: ['./caixa-tela.component.css']
})
export class CaixaTelaComponent implements OnInit {

  barcodeNumber: string | undefined;
  quantidade: number = 1;
  valorTotal: number = 0;
  barcode: Barcode = new Barcode();
  informacoesDoProduto: String = "";

  listaProdutos: ProdutoCompra[] = [];
  valorTotalDaCompra: number = 0;

  erro: boolean = false;
  success: boolean = false;
  cancelado : boolean = false;
  canceladoDescription : String = "Compra cancelada";
  erroDescription: String = "Código de barras não encontrado";
  successDescription: String = "Compra finalizada!";

  constructor(
    private service: CaixaTelaService
  ) {

  }

  ngOnInit(): void { }

  searchBarcode() {

    this.cancelado = false;
    this.erro = false;
    this.success = false;

    this.service.getBarcode(this.barcodeNumber)
      .subscribe(response => {

        this.barcode = response;
        this.valorTotal = this.barcode.price * this.quantidade;
        this.preparaInformacoesDoProduto(this.barcode);

      }, errorResponse => {
        this.erro = true;
      });
  }

  quantidadeChange(event: number) {

    this.quantidade = event
    this.valorTotal = this.barcode.price * this.quantidade;

  }

  preparaInformacoesDoProduto(barcode: Barcode) {
    let name = barcode.name;
    let brand = barcode.brand;
    let category = barcode.category;
    let weight = this.preparaQuantidade(barcode.weight, barcode.weightType);

    this.informacoesDoProduto = "NOME: " + name + "\n                            MARCA: " + brand +
      "\n                            CATEGORIA: " + category + "\n                            QUANTIDADE: " + weight;
    console.log(this.informacoesDoProduto);

  }

  preparaQuantidade(qtd: any, tipo: any) {
    let qtdReturn: String;
    if (qtd.weight === 0) {
      qtdReturn = "-";
    } else {
      qtdReturn = qtd + " " + tipo;
    }
    return qtdReturn;
  }

  btnIncluir() {
    if (this.barcode.name) {
      let produto: ProdutoCompra = new ProdutoCompra();
      produto.nomeProduto = this.barcode.name;
      produto.precoUnitario = this.barcode.price;
      produto.quantidade = this.quantidade;
      produto.precoTotal = this.valorTotal;
      this.listaProdutos.push(produto);

      this.valorTotalDaCompra = this.valorTotalDaCompra + this.valorTotal;

      this.barcode = new Barcode();
      this.valorTotal = 0;
      this.informacoesDoProduto = "";
      this.barcodeNumber = undefined;
      this.quantidade = 1;
    }
  }

  finalizarCompra() {
    this.cancelado = false;
    this.erro = false;
    this.success = true;
    this.listaProdutos = [];
    this.barcode = new Barcode();
    this.valorTotal = 0;
    this.informacoesDoProduto = "";
    this.barcodeNumber = undefined;
    this.quantidade = 1;
    this.valorTotalDaCompra = 0;
  }

  cancelarCompra() {
    this.success = false;
    this.erro = false;
    this.cancelado = true;
    this.listaProdutos = [];
    this.barcode = new Barcode();
    this.valorTotal = 0;
    this.informacoesDoProduto = "";
    this.barcodeNumber = undefined;
    this.quantidade = 1;
    this.valorTotalDaCompra = 0;
  }

}
