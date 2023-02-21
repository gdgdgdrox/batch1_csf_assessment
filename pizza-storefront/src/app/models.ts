// Add your models here if you have any
export interface Order{
    name: string,
    email: string,
    base: boolean,
    comments: string,
    sauce: string,
    size: number,
    toppings: string[]
}

export interface OrderSummary{
    order_id: number,
    amount: number

}