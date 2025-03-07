import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms'; 
import { PasswordModule } from 'primeng/password'; 
import { InputTextModule } from 'primeng/inputtext';
import { CardModule } from 'primeng/card';
import { PanelModule } from 'primeng/panel';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { DividerModule } from 'primeng/divider';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { MessagesModule } from 'primeng/messages';
import { AccordionModule } from 'primeng/accordion';
import { RadioButtonModule } from 'primeng/radiobutton';
import { CalendarModule } from 'primeng/calendar';
import { TabViewModule } from 'primeng/tabview';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { AvatarModule } from 'primeng/avatar';
import { StepperModule } from 'primeng/stepper';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { ImageModule } from 'primeng/image';
import { CheckboxModule } from 'primeng/checkbox';

const primengModules = [
  ButtonModule,
  FormsModule,
  PasswordModule,
  InputTextModule,
  CardModule,
  InputTextareaModule,
  DropdownModule,
  PanelModule,
  DividerModule,
  TableModule,
  ToastModule,
  ToolbarModule,
  MessagesModule,
  AccordionModule,
  RadioButtonModule,
  CalendarModule,
  TabViewModule,
  ConfirmDialogModule,
  DialogModule,
  AvatarModule,
  MessagesModule,
  StepperModule,
  StepperModule,
  ToggleButtonModule,
  IconFieldModule,
  InputIconModule,
  ImageModule,
  CheckboxModule
];

@NgModule({
  imports: [
    CommonModule,
    ...primengModules
  ],
  exports: [
   ...primengModules
  ]
})
export class PrimeNgModule { }
