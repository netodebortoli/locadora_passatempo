import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {NgxMaskModule} from 'ngx-mask';

import {AppMaterialModule} from './app-material/app-material.module';
import {BaseContainerComponent} from './base/components/base-container/base-container.component';
import {BaseListComponent} from './base/components/base-list/base-list.component';
import {ConfirmationDialogComponent} from './components/confirmation-dialog/confirmation-dialog.component';
import {ErrorDialogComponent} from './components/error-dialog/error-dialog.component';
import {PageNotFoundComponent} from './components/page-not-found/page-not-found.component';
import {RouterModule} from '@angular/router';
import {ArraySortPipe} from './pipes/array-sort.pipe';
import { CpfPipe } from './pipes/cpf.pipe';
import { TelefonePipe } from './pipes/telefone.pipe';

@NgModule({
    declarations: [
        //componentes
        ErrorDialogComponent,
        PageNotFoundComponent,
        ConfirmationDialogComponent,
        BaseListComponent,
        BaseContainerComponent,
        ArraySortPipe,
        CpfPipe,
        TelefonePipe,
    ],
    imports: [
        //módulos
        RouterModule,
        CommonModule,
        AppMaterialModule,
        NgxMaskModule.forRoot(),
    ],
    exports: [
        ErrorDialogComponent,
        ConfirmationDialogComponent,
        ArraySortPipe,
        CpfPipe,
        TelefonePipe,
        NgxMaskModule
    ]
})
export class SharedModule {
}
