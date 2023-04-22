import {Component, OnInit, Inject } from '@angular/core';
import { Veiculo } from '../Veiculo';
import { VeiculoService } from 'src/app/veiculo.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-veiculos-list',
  templateUrl: './veiculos-list.component.html',
  styleUrls: ['./veiculos-list.component.css']
})
export class VeiculosListComponent implements OnInit {

  veiculos : Veiculo[] = []
  id: number = 0;
  selectedVeiculo : Veiculo = new Veiculo();
  success : string = '';
  failed : string = '';

  constructor(private service: VeiculoService) { }

  ngOnInit(): void {
    this.service
      .getVeiculos()
      .subscribe( res => this.veiculos = res )
  }

  preDelete(veiculo : Veiculo){
    this.selectedVeiculo = veiculo;
    this.service.delete(this.selectedVeiculo)
    .subscribe(
      res => {this.success = 'Veiculo successfully deleted',
      this.ngOnInit();
    },
      erro => this.failed = 'There was an error deleting the Veiculo'
      )
  }

  deleteVeiculo(){
    this.service.delete(this.selectedVeiculo)
    .subscribe(
      res => {this.success = 'Veiculo successfully deleted',
      this.ngOnInit();
    },
      erro => this.failed = 'There was an error deleting the Veiculo'
      )

  }


}
