import { PizzaService } from './../pizza.service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Order } from '../models';
import { Router } from '@angular/router';

const SIZES: string[] = [
  "Personal - 6 inches",
  "Regular - 9 inches",
  "Large - 12 inches",
  "Extra Large - 15 inches"
]

const PizzaToppings: string[] = [
    'chicken', 'seafood', 'beef', 'vegetables',
    'cheese', 'arugula', 'pineapple'
]

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  form!: FormGroup;
  toppingArray!: FormArray;

  pizzaSize = SIZES[0]

  @ViewChild('size')
  sizeRef!: ElementRef

  constructor(private fb: FormBuilder, private pizzaSvc: PizzaService, private router: Router) {}

  ngOnInit(): void {
    this.toppingArray = this.fb.array([], Validators.required);
    this.form = this.fb.group({
      name: this.fb.control('Foo Guo Dong', Validators.required),
      email: this.fb.control('gdfoo94@gmail.com', [Validators.required, Validators.email]),
      size: this.fb.control(this.pizzaSize, Validators.required),
      base: this.fb.control('', Validators.required),
      sauce: this.fb.control('', Validators.required),
      toppings: this.toppingArray,
      comments: this.fb.control('nil')
    })
  }

  updateSize(size: string) {
    this.pizzaSize = SIZES[parseInt(size)]
  }

  placeOrder(){
    const order: Order = {
      name: this.form.value.name,
      email: this.form.value.email,
      base: this.form.value.base == 'thick' ? true : false,
      comments: this.form.value.comments,
      sauce: this.form.value.sauce,
      size: this.form.value.size,
      toppings: this.form.value.toppings
    }
    console.log('order obj: ', order);

    //post order to server
    this.pizzaSvc.createOrder(order).then(response => console.log(response)).catch(error => console.log(error));

    //if order was successfully placed, navigate to View 1 to list orders
    this.router.navigate(['/orders', order.email]);

    //if order failed, alert the user

  }

  cbChange(event: any){
  if (event.target.checked) {
    this.toppingArray.push(this.fb.control(event.target.value));
  } else {
    let i: number = 0;
    this.toppingArray.controls.forEach((item: any) => {
      if (item.value == event.target.value) {
        this.toppingArray.removeAt(i);
        return;
      }
      i++;
    });
  }
  }
}