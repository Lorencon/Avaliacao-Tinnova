import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Veiculo } from './veiculo-items/Veiculo';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VeiculoService {

  constructor(private http : HttpClient) { }

  insert( veiculo : Veiculo) : Observable<Veiculo>{
    let obj = {
        "veiculo": veiculo.veiculo,
        "marca": veiculo.marca,
        "ano": veiculo.ano,
        "descricao": veiculo.descricao,
        "vendido": veiculo.vendido,
        "created": veiculo.created,
        "updated": veiculo.updated,
        "cor": veiculo.cor,
    }

    return this.http.post<Veiculo>('http://localhost:8080/api/veiculos', obj)

  }


  getVeiculos() : Observable<any[]> {
    return this.http.get<Veiculo[]>('http://localhost:8080/api/veiculos')

  }

  getVeiculoById(id : number) : Observable<Veiculo>{
    return this.http.get<any>(`http://localhost:8080/api/veiculos/${id}`)
  }

  update(id : number, veiculo : Veiculo) : Observable<Veiculo> {
    let obj = {
        "veiculo": veiculo.veiculo,
        "marca": veiculo.marca,
        "ano": veiculo.ano,
        "descricao": veiculo.descricao,
        "vendido": veiculo.vendido,
        "created": veiculo.created,
        "updated": veiculo.updated,
        "cor": veiculo.cor,
    }
    return this.http.put<Veiculo>(`http://localhost:8080/api/veiculos/${id}`, obj)
  }

  delete(veiculo : Veiculo) : Observable<any>{
    return this.http.delete<any>(`http://localhost:8080/api/veiculos/${veiculo.id}`)
  }

}
