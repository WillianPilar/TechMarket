import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Barcode } from './barcode/barcode';
import { ProdutoNoEstoqueRequest } from './estoque/produto-no-estoque-request';
import { ProdutoNoEstoqueResponse } from './estoque/produto-no-estoque-response';

@Injectable({
  providedIn: 'root'
})
export class EstoqueService {

  constructor( private http : HttpClient ) { }

  getAllEstoque() {
    return this.http.get<ProdutoNoEstoqueResponse[]>("http://localhost:8082/techmarket/estoque/api/v1/search-all");
  }

  getBarcodeByBarcode(barCode: Barcode) : Observable<Barcode> {
    return this.http.get<any>(`http://localhost:8081/techmarket/barcode/api/v1/search/${barCode.barCode}`);
  }

  salvarProdutoNoEstoque(produtoRequest: ProdutoNoEstoqueRequest) : Observable<ProdutoNoEstoqueResponse>{
    return this.http.post<ProdutoNoEstoqueResponse>("http://localhost:8082/techmarket/estoque/api/v1/create", produtoRequest);
  }

  updateProduct(produtoRequest: ProdutoNoEstoqueRequest, productId: number | undefined) : Observable<any> {
    return this.http.put(`http://localhost:8082/techmarket/estoque/api/v1/update/${productId}`, produtoRequest);
  }

  getProductById(productId: any) : Observable<ProdutoNoEstoqueResponse> {
    return this.http.get<any>(`http://localhost:8082/techmarket/estoque/api/v1/search/${productId}`);
  }

  deletarProduto(produto : ProdutoNoEstoqueResponse) : Observable<any>{
    return this.http.delete<ProdutoNoEstoqueResponse>(`http://localhost:8082/techmarket/estoque/api/v1/delete/${produto.productId}`);
  }
}
