import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FuncionariosService } from 'src/app/funcionarios.service';
import { Funcionario } from '../funcionario';

@Component({
  selector: 'app-funcionarios-list',
  templateUrl: './funcionarios-list.component.html',
  styleUrls: ['./funcionarios-list.component.css']
})
export class FuncionariosListComponent implements OnInit {

  funcionarios : Funcionario[] = [];
  funcionarioSelecionado : Funcionario = new Funcionario();
  deletadoSucesso : String | undefined;
  deletadoErro : String | undefined;

  constructor(
    private service : FuncionariosService,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.getAllFuncionarios();
  }

  getAllFuncionarios(){
    this.service
      .getFuncionarios()
      .subscribe( resposta => {
        this.funcionarios = resposta;
      });
  }

  novoCadastro(){
    this.router.navigate(["/funcionarios-form"]);
  }

  preparaDelecaoDoFuncionario( funcionario : Funcionario ){
    this.funcionarioSelecionado = funcionario;
  }

  deletarFuncionario(){
    this.service.deletarFuncionario(this.funcionarioSelecionado).subscribe( response => {
      this.deletadoSucesso = "Funcionário deletado com sucesso";
      this.deletadoErro = undefined;
      this.getAllFuncionarios();
    }, errorResponse => {
      this.deletadoErro = "Erro ao deletar o funcionário";
      this.deletadoSucesso = undefined;
    }
    );
  }

}
