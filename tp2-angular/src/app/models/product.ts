export interface Product {
    id:number;
    traderId:string;
    name:string;
    price:number;
    description:string;
    hidden:boolean;
    sold:boolean;
}

export class editProductForm{
    constructor(private id:number,
        private name:string,
        private price:number,
        private description:string,
        private hidden:boolean){}
}

export class NewProduct {
    constructor(
        private name:string,
        private price:number,
        private description:string,
        private hidden:boolean){}
}