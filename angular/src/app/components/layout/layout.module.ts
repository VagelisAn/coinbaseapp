import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { HomeComponent } from "./home/home.component";
import { SidebarComponent } from "./sidebar/sidebar.component";
import { BrowserModule } from "@angular/platform-browser";
import { AppRoutingModule } from "src/app/app-routing.module";
import { RegisterComponent } from "./register/register.component";
import { ReactiveFormsModule } from "@angular/forms";
import { PrimeNgModule } from "src/app/prime.ng.module";

const layoutModules = [
    HomeComponent,
    SidebarComponent,
    RegisterComponent
];

@NgModule({
    declarations: [...layoutModules],
    imports: [
        CommonModule,
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        PrimeNgModule
    ],
    exports: [...layoutModules],
})
export class LayoutModules { }