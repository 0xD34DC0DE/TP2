import { Cart } from './cart';
import { Status } from './status-enum';

export interface Account{
    email:string;
    firstName:string;
    lastName:string;
    address:string;
    phone:string;
    type:string;
    status:Status;
}

export interface Traders extends Account{
    cart:Cart;
    balance:number;
}