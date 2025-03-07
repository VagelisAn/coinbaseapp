import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { HomeComponent } from "./home/home.component";
import { SidebarComponent } from "./sidebar/sidebar.component";
import { BrowserModule } from "@angular/platform-browser";
import { AppRoutingModule } from "src/app/app-routing.module";

const layoutModules = [
    HomeComponent,
    SidebarComponent
];

@NgModule({
    declarations: [...layoutModules],
    imports: [
        CommonModule,
        BrowserModule,
        AppRoutingModule
    ],
    exports: [...layoutModules],
})
export class LayoutModules { }