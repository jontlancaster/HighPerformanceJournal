import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {RouterModule, Routes} from '@angular/router';

import {AppComponent} from './app.component';
import {LoginComponent} from './login.component';
import {EntryComponent} from './entry.component';
import {ReportsComponent} from './reports.component';
import {AdminComponent} from './admin.component';
import {EntryService} from './services/entry.service';

const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'entry', component: EntryComponent},
  {path: 'reports', component: ReportsComponent},
  {path: 'admin', component: AdminComponent}
];


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    EntryComponent,
    ReportsComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [EntryService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
