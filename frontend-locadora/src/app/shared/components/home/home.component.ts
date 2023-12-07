import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

import { AppMaterialModule } from '../../app-material/app-material.module';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, AppMaterialModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

}
