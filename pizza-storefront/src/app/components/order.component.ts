import { PizzaService } from './../pizza.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderSummary } from '../models';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  email!: string;
  orderSummaries: OrderSummary[] = [];

  constructor(private pizzaSvc: PizzaService, private activatedRoute: ActivatedRoute) { }
  ngOnInit(): void {
    this.email = this.activatedRoute.snapshot.params['email'];
    this.pizzaSvc.getOrders(this.email)
    .then((response:any) => {
      this.orderSummaries = response[this.email];
      console.log(this.orderSummaries);
    })
    .catch(error => console.log(error));
  }

}
