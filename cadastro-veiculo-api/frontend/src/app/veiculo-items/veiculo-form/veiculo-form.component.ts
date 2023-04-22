import {Component, OnInit } from '@angular/core';
import { Veiculo } from '../Veiculo';
import { VeiculoService } from 'src/app/veiculo.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-veiculo-form',
  templateUrl: './veiculo-form.component.html',
  styleUrls: ['./veiculo-form.component.css'],
  providers: [DatePipe]
})
export class VeiculoFormComponent implements OnInit {

  veiculo : Veiculo;
  success: boolean = false;
  errors: String[] = [];
  id : number = 1;

  constructor(
    private service : VeiculoService,
    private activatedRoute : ActivatedRoute,
    private datePipe: DatePipe
    ) {
    this.veiculo = new Veiculo();
   }

  ngOnInit(): void {
    let params : Observable<any> =  this.activatedRoute.params;
    params.subscribe(urlParams=>{
      this.id = urlParams['id'];
      if(this.id){
        this.service
        .getVeiculoById(this.id)
        .subscribe(
          response => this.veiculo = response,
          errorResponse => this.veiculo = new Veiculo()
        )
      }


    })
  }

  onSubmit(){

    if(this.id){
      this.veiculo.updated = this.datePipe.transform(new Date(), 'dd/MM/yyyy');
      this.service.update(this.id, this.veiculo)
      .subscribe( res => {
        this.success = true;
        this.errors = [];
      }
      )
    }
    else{
      this.veiculo.created = this.datePipe.transform(new Date(), 'dd/MM/yyyy');
      this.service
        .insert(this.veiculo)
        .subscribe( res =>{
          this.success = true;
          this.errors = [];

        }, errorRes =>{
          this.success = false;
          this.errors = errorRes.error.errors

        }

        )
    }

  }

}
