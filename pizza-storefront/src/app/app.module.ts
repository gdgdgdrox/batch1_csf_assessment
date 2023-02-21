import { PizzaService } from './pizza.service';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { OrderComponent } from './components/order.component';
import { HttpClientModule } from '@angular/common/http';


const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'orders/:email', component: OrderComponent},
  {path: '**', redirectTo: '/', pathMatch:'full'}
] 

@NgModule({
  declarations: [
    AppComponent, MainComponent, OrderComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    ReactiveFormsModule,
    HttpClientModule
  ],

  providers: [ PizzaService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
