// Implement the methods in PizzaService for Task 3
// Add appropriate parameter and return type 

import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";
import { Order, OrderSummary } from "./models";

@Injectable()
export class PizzaService {

  constructor(private httpClient: HttpClient) { }

  // POST /api/order
  // Add any required parameters or return type
  createOrder(order : Order) { 
    const headers = new HttpHeaders().set('content-type','application/json')
    return firstValueFrom(this.httpClient.post('http://localhost:8080/api/order', order, {headers}));

  }

  // GET /api/order/<email>/all
  // Add any required parameters or return type
  getOrders(email: string) { 
    return firstValueFrom(this.httpClient.get(`http://localhost:8080/api/order/${email}/all`));
  }

}
