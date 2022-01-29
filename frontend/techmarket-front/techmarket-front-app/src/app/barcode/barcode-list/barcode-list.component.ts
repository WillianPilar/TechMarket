import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BarcodeService } from 'src/app/barcode.service';
import { Barcode } from '../barcode';

@Component({
  selector: 'app-barcode-list',
  templateUrl: './barcode-list.component.html',
  styleUrls: ['./barcode-list.component.css']
})
export class BarcodeListComponent implements OnInit {

  barcodes : Barcode[] = [];
  barcodeSelecionado : Barcode =  new Barcode();
  deletadoSucesso : String | undefined;
  deletadoErro : String | undefined;

  constructor(
    private service : BarcodeService,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.getAllBarCodes();
  }

  getAllBarCodes(){
    this.service.getBarcodes()
      .subscribe( response => {
        this.barcodes = response;
        this.formatarPeso();
      });
  }

  novoCadastro(){
    this.router.navigate(["/barcode-form"]);
  }

  formatarPeso() {
    this.barcodes.forEach( barcode =>{
      if (barcode.weight === 0) {
        barcode.weight = "-";
      } else {
        barcode.weight = barcode.weight + " " + barcode.weightType;
      }
    });
  }

  preparaDelecaoBarcode(barcode : Barcode){
    this.barcodeSelecionado = barcode;
  }

  deletarBarcode(){

    this.service.deletarBarcode(this.barcodeSelecionado)
      .subscribe( response => {
        this.deletadoSucesso = "Código de barras deletado com sucesso";
        this.deletadoErro = undefined;
        this.getAllBarCodes();
      }, errorResponse => {
        this.deletadoErro = "Erro ao deletar o código de barras";
        this.deletadoSucesso = undefined;
      });

  }
    
}