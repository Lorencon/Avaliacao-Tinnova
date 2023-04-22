import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'

import { VeiculoRoutingModule } from './veiculo-routing.module'
import { VeiculoFormComponent } from './veiculo-form/veiculo-form.component'
import { VeiculosListComponent } from './veiculos-list/veiculos-list.component';


@NgModule({
  declarations: [
    VeiculoFormComponent,
    VeiculosListComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    VeiculoRoutingModule
  ], exports: [
    VeiculoFormComponent,
    VeiculosListComponent
  ]
})
export class VeiculoItemsModule {


}
