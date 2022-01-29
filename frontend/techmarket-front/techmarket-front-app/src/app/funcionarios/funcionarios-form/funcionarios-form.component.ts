import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FuncionariosService } from 'src/app/funcionarios.service';
import { Funcionario } from '../funcionario';

@Component({
  selector: 'app-funcionarios-form',
  templateUrl: './funcionarios-form.component.html',
  styleUrls: ['./funcionarios-form.component.css']
})
export class FuncionariosFormComponent implements OnInit {

  success : boolean = false;
  errors : String[] =[];
  funcionario : Funcionario;
  funcionarioId : number | undefined;

  constructor(
    private service : FuncionariosService,
    private router : Router,
    private activatedRoute : ActivatedRoute
    ) {
    this.funcionario = new Funcionario();
   }

  ngOnInit(): void {
    let params = this.activatedRoute.params;
    params.subscribe(param =>{
      if (param.id) {
        this.funcionarioId = param.id;
        this.service.getFuncionarioById(param.id)
          .subscribe( response => {
            this.funcionario = response;
          },errorResponse => {
            this.funcionario = new Funcionario();
          });
      }
    });

  }

  onSubmit(){

    if (this.funcionarioId != null) {
      this.service.update(this.funcionario)
        .subscribe( response => {
          this.success = true;
          this.errors = [];
        }, errorResponse => {
          this.success = false;
          this.errors = errorResponse.error.errors;
        }
        
        );
    }else{
      this.service.salvarFuncionario(this.funcionario)

        .subscribe( response =>{

          this.success = true;
          this.errors = [];
          this.funcionario = response;
          this.funcionarioId = this.funcionario.id;

        }, errorResponse => {

          this.success = false;
          this.funcionario.id = undefined;
          if (errorResponse.error.errors === undefined) {
            this.errors = ["Erro ao salvar o funcionário"];
          }else{
            this.errors = errorResponse.error.errors;
          }

        })
    }

  }
  voltarParaListaClientes(){
    this.router.navigate(["/funcionarios-list"]);
  }

}
