import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SociosComponent} from "./containers/socios/socios.component";
import {clienteResolver} from "./guards/cliente.resolver";
import {SocioFormComponent} from "./containers/socio-form/socio-form.component";

const routes: Routes = [
    {path: '', component: SociosComponent},
    {
        path: 'novo',
        component: SocioFormComponent,
        resolve: {socio: clienteResolver}
    },
    {
        path: 'editar/:id',
        component: SocioFormComponent,
        resolve: {socio: clienteResolver}
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ClientesRoutingModule {
}
