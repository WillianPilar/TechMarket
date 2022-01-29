import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BarcodeService } from 'src/app/barcode.service';
import { Barcode } from '../barcode';

@Component({
  selector: 'app-barcode-form',
  templateUrl: './barcode-form.component.html',
  styleUrls: ['./barcode-form.component.css']
})
export class BarcodeFormComponent implements OnInit {
  
  success : boolean = false;
  errors : String[] = [];
  barcode : Barcode;
  idBarcode : String | undefined;

  constructor(
    private service : BarcodeService,
    private router : Router,
    private activatedRoute : ActivatedRoute
  ) { 
    this.barcode = new Barcode();
  }

  ngOnInit(): void {
    let params = this.activatedRoute.params;
    params.subscribe( param => {
      if(param.barcode){
        this.idBarcode = param.barcode;
        this.service.getBarcodeByBarcode(param.barcode)
          .subscribe( response => {
            this.barcode = response;
          }, errorResponse => {
            this.barcode = new Barcode();
            this.idBarcode = undefined;
          });
      }
    });
  }

  onSubmit(){
    if (this.idBarcode != null) {
      this.service.updateBarcode(this.barcode)
        .subscribe( response => {
          this.success = true;
          this.errors = [];
        }, errorResponse => {
          this.success = false;
          this.errors = errorResponse.error.errors;
        });
    } else {
      this.service.salvarBarcode(this.barcode)
        .subscribe( response => {
          
          this.success = true;
          this.errors = [];
          this.idBarcode = response.barCode;
        
        }, errorResponse => {

          this.success = false
          this.idBarcode = undefined;
          this.errors = errorResponse.error.errors;

        });
    }
  }

  voltarParaLista(){
    this.router.navigate(["/barcode-list"]);
  }

}
