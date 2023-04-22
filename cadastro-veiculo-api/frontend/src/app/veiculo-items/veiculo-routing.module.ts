import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VeiculoFormComponent } from './veiculo-form/veiculo-form.component';
import { VeiculosListComponent } from './veiculos-list/veiculos-list.component'

const routes: Routes = [
  { path : 'veiculos-form', component : VeiculoFormComponent },
  { path : ':id/veiculos-form', component : VeiculoFormComponent },
  { path : 'veiculos-list', component: VeiculosListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VeiculoRoutingModule { }
