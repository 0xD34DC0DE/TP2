import { Cart } from './cart';
import { Status } from './status-enum';

export class Account{
    email:string;
    firstName:string;
    lastName:string;
    address:string;
    phone:string;
    type:string;
    roles: string[];
    status:Status;
}
export interface AccountInter{
    email:string;
    firstName:string;
    lastName:string;
    address:string;
    phone:string;
    type:string;
    roles: string[];
    status:Status;
}

export class AccountForm{
    constructor(private email:string,
        private firstName:string,
        private lastName:string,
        private address:string,
        private phone:string){}
}

export interface Traders extends Account{
    cart:Cart;
    balance:number;
}