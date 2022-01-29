import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Barcode } from './barcode/barcode';

@Injectable({
  providedIn: 'root'
})
export class CaixaTelaService {
  
  constructor(
    private http : HttpClient
  ) { }
  
  getBarcode(barcodeNumber: string | undefined) : Observable<Barcode> {
    return this.http.get<any>(`http://localhost:8081/techmarket/barcode/api/v1/search/${barcodeNumber}`);
  }

}
