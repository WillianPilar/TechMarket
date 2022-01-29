import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Barcode } from 'src/app/barcode/barcode';
import { EstoqueService } from 'src/app/estoque.service';
import { ProdutoNoEstoqueRequest } from '../produto-no-estoque-request';
import { ProdutoNoEstoqueResponse } from '../produto-no-estoque-response';

@Component({
  selector: 'app-estoque-form',
  templateUrl: './estoque-form.component.html',
  styleUrls: ['./estoque-form.component.css']
})
export class EstoqueFormComponent implements OnInit {

  success: boolean = false;
  errors: String[] = [];
  produtoIsPresent: boolean = false;
  produto: ProdutoNoEstoqueResponse;
  produtoRequest: ProdutoNoEstoqueRequest;
  barcode: Barcode;
  barcodeIsPresent: boolean;
  barcodePesquisaIsError: boolean;

  constructor(
    private service: EstoqueService,
    private route: Router,
    private activatedRoute : ActivatedRoute
  ) {
    this.produto = new ProdutoNoEstoqueResponse();
    this.produtoRequest = new ProdutoNoEstoqueRequest();
    this.barcode = new Barcode();
    this.barcodeIsPresent = false;
    this.barcodePesquisaIsError = false;
  }

  ngOnInit(): void {
    
    let params = this.activatedRoute.params;
    params.subscribe(param => {
      if (param.productId) {

        this.service.getProductById(param.productId)
          .subscribe(response => {

            this.produto = response;
            this.produtoIsPresent = true;
            this.montarProdutoRequest(this.produto);

            this.barcode.barCode = this.produto.barCode;
            this.service.getBarcodeByBarcode(this.barcode)
              .subscribe(response => {
                this.barcode = response;
                this.barcodeIsPresent = true;
              });

          });
        console.log("OI");

      }
    });

  }

  searchBarcode(barcode: Barcode) {

    this.service.getBarcodeByBarcode(barcode)
      .subscribe(response => {

        this.barcode = response;
        this.barcodeIsPresent = true;
        this.barcodePesquisaIsError = false;

      }, errorResponse => {

        this.barcodePesquisaIsError = true;
        this.barcodeIsPresent = false;

      });

  }

  novoBarCode() {
    this.route.navigate(['/barcode-form']);
  }

  voltarParaLista() {
    this.route.navigate(['/estoque-list']);
  }

  onSubmit() {

    if (this.produtoIsPresent) {

      this.produtoRequest.funciuonarioAtualizou = "000002";
      console.log(this.produtoRequest);

      this.service.updateProduct(this.produtoRequest, this.produto.productId)
        .subscribe( response => {

          this.success = true;

        }, errorResponse => {

          this.success = false
          this.errors = errorResponse.error.errors;
          console.log(errorResponse);

        });

    } else {

      this.produtoRequest.barCode = this.barcode.barCode;
      this.produtoRequest.preco = this.barcode.price;
      this.produtoRequest.funcionarioRecebeu = "000001";

      this.service.salvarProdutoNoEstoque(this.produtoRequest)
        .subscribe(response => {

          this.success = true;
          this.errors = [];
          this.produto = response;
          this.produtoIsPresent = true;

        }, errorResponse => {

          this.success = false
          this.errors = errorResponse.error.errors;
          this.produtoIsPresent = false;

        });
    }
  }

  montarProdutoRequest(produto : ProdutoNoEstoqueResponse){

    this.produtoRequest.barCode = produto.barCode;
    this.produtoRequest.dtHrFabricacao = produto.dtHrFabricacao;
    this.produtoRequest.dtValidade = produto.dtValidade;
    this.produtoRequest.funcionarioRecebeu = produto.funcionarioRecebeu;
    this.produtoRequest.funciuonarioAtualizou = produto.funciuonarioAtualizou;
    this.produtoRequest.notaFiscal = produto.notaFiscal;
    this.produtoRequest.nrLote = produto.nrLote;
    this.produtoRequest.preco = produto.precoAtual;
    this.produtoRequest.precoDeCompra = produto.precoDeCompra;
    this.produtoRequest.quantidade = produto.quantidade;

  }


}
