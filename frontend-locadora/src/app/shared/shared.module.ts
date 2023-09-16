import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from './app-material/app-material.module';
import { BaseListComponent } from './base/components/base-list/base-list.component';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
import { ErrorDialogComponent } from './components/error-dialog/error-dialog.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { CategoriaPipe } from './pipes/categoria.pipe';

@NgModule({
  declarations: [
    //componentes
    ErrorDialogComponent,
    CategoriaPipe,
    PageNotFoundComponent,
    ConfirmationDialogComponent,
    BaseListComponent
  ],
  imports: [
    //módulos
    CommonModule,
    AppMaterialModule
  ],
  exports: [
    ErrorDialogComponent,
    ConfirmationDialogComponent,
    CategoriaPipe
  ]
})
export class SharedModule { }
