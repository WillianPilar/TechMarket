import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Barcode } from './barcode/barcode';

@Injectable({
  providedIn: 'root'
})
export class BarcodeService {

  constructor( private http : HttpClient) { }

  salvarBarcode(barCode : Barcode) : Observable<Barcode> {
    return this.http.post<Barcode>("http://localhost:8081/techmarket/barcode/api/v1/create", barCode);
  }

  getBarcodes() : Observable<Barcode[]> {
    return this.http.get<Barcode[]>("http://localhost:8081/techmarket/barcode/api/v1/search-all");
  }

  getBarcodeByBarcode(barCode: Barcode) : Observable<Barcode> {
    return this.http.get<any>(`http://localhost:8081/techmarket/barcode/api/v1/search/${barCode}`);
  }

  updateBarcode(barcode: Barcode) : Observable<any> {
    return this.http.put<Barcode>(`http://localhost:8081/techmarket/barcode/api/v1/update/${barcode.barCode}`, barcode);
  }

  deletarBarcode(barcodeSelecionado: Barcode) : Observable<any> {
    return this.http.delete<Barcode>(`http://localhost:8081/techmarket/barcode/api/v1/delete/${barcodeSelecionado.barCode}`);
  }
}
