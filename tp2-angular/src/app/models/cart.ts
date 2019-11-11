import { Product } from './product';

export interface Cart{
    cartId:number;
    productList:Product[];
}