import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CaixaResponse } from './caixa/caixa-response';
import { CaixaUpdateRequest } from './caixa/caixa-update-request';
import { NewCaixaRequest } from './caixa/new-caixa-request';

@Injectable({
  providedIn: 'root'
})
export class CaixasService {
  
  constructor( private http : HttpClient) { }
  
  getAllCaixas(){
    return this.http.get<CaixaResponse[]>("http://localhost:8083/techmarket/caixa/api/v1/search-all");
  }
  
  createNewCaixa(newCaixaRequest : NewCaixaRequest) : Observable<CaixaResponse>{
    return this.http.post<CaixaResponse>("http://localhost:8083/techmarket/caixa/api/v1/create", newCaixaRequest);
  }
  
  getCaixaByNumber(caixaNumber: any) : Observable<CaixaResponse> {
    return this.http.get<any>(`http://localhost:8083/techmarket/caixa/api/v1/search/${caixaNumber}`);
  }
  
  updateCaixa(caixaUpdateRequest: CaixaUpdateRequest, numeroDoCaixa: number | undefined) : Observable<any> {
    return this.http.put(`http://localhost:8083/techmarket/caixa/api/v1/update/${numeroDoCaixa}`, caixaUpdateRequest);
  }
  
  deletarCaixa(numeroDoCaixa: number | undefined) : Observable<any> {
    return this.http.delete<number>(`http://localhost:8083/techmarket/caixa/api/v1/delete/${numeroDoCaixa}`);
  }

}
