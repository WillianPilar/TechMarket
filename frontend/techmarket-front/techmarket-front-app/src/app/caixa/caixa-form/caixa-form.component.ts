import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CaixasService } from 'src/app/caixas.service';
import { CaixaResponse } from '../caixa-response';
import { CaixaUpdateRequest } from '../caixa-update-request';
import { NewCaixaRequest } from '../new-caixa-request';

@Component({
  selector: 'app-caixa-form',
  templateUrl: './caixa-form.component.html',
  styleUrls: ['./caixa-form.component.css']
})
export class CaixaFormComponent implements OnInit {

  caixaIsPresent : boolean = false;
  requestNewCaixa : NewCaixaRequest =  new NewCaixaRequest();
  caixaResponse : CaixaResponse = new CaixaResponse();
  caixaUpdateRequest : CaixaUpdateRequest = new CaixaUpdateRequest();

  constructor(
    private router : Router,
    private activatedRoute : ActivatedRoute,
    private service : CaixasService
  ) { }

  ngOnInit(): void {
    let params = this.activatedRoute.params;
    params.subscribe( param => {
      if (param.caixaNumber) {
      
        this.service.getCaixaByNumber(param.caixaNumber)
          .subscribe(response => {

            this.caixaResponse = response;
            this.caixaIsPresent = true;

          });
      }else{
        this.caixaIsPresent = false;
      }
    });
  }

  onSubmit(){
    
    if (this.caixaIsPresent) {

      this.caixaUpdateRequest.status = this.caixaResponse.status;
      this.caixaUpdateRequest.fundoDeTroco = this.caixaResponse.fundoDeTroco;
      this.service.updateCaixa(this.caixaUpdateRequest, this.caixaResponse.numeroDoCaixa)
        .subscribe(response => {
          console.log(response);
        });

    } else {
      this.service.createNewCaixa(this.requestNewCaixa)
      .subscribe(response => {
        console.log(response);
      });
    }

  }

  voltarParaLista(){
    this.router.navigate(['/caixa-list']);
  }

}
