import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorDialogComponent } from './components/error-dialog/error-dialog.component';
import { AppMaterialModule } from './app-material/app-material.module';
import { CategoriaPipe } from './pipes/categoria.pipe';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
import { BaseListComponent } from './base/components/base-list/base-list.component';

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
