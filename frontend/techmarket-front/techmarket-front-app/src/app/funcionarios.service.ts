import { Injectable } from '@angular/core';
import { Funcionario } from './funcionarios/funcionario';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FuncionariosService {

  constructor( private http : HttpClient ) { }

  salvarFuncionario( funcionario : Funcionario) : Observable<Funcionario>{
    return this.http.post<Funcionario>("http://localhost:8080/techmarket/employees/api/v1/create", funcionario);
  }

  getFuncionarios() : Observable<Funcionario[]> {
   return this.http.get<Funcionario[]>("http://localhost:8080/techmarket/employees/api/v1/search-all");
  }

  getFuncionarioById(id: number) : Observable<Funcionario> {
    return this.http.get<any>(`http://localhost:8080/techmarket/employees/api/v1/search/${id}`);
  }

  update(funcionario: Funcionario) : Observable<any>{
    return this.http.put<Funcionario>(`http://localhost:8080/techmarket/employees/api/v1/update/${funcionario.id}`, funcionario);
  }

  deletarFuncionario(funcionarioSelecionado: Funcionario) : Observable<any>{
    return this.http.delete<Funcionario>(`http://localhost:8080/techmarket/employees/api/v1/delete/${funcionarioSelecionado.id}`);
  }
}
