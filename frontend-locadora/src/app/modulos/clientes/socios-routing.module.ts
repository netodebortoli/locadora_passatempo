import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SociosComponent} from "./containers/socios/socios.component";
import {socioResolver} from "./guards/socio.resolver";
import {SocioFormComponent} from "./containers/socio-form/socio-form.component";

const routes: Routes = [
    {path: '', component: SociosComponent},
    {
        path: 'novo',
        component: SocioFormComponent,
        resolve: {socio: socioResolver}
    },
    {
        path: 'editar/:id',
        component: SocioFormComponent,
        resolve: {socio: socioResolver}
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SociosRoutingModule {
}
