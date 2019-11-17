export interface Product{
    id:number;
    traderId:string;
    name:string;
    price:number;
    availableStock:string;
    available:boolean;
}

export class editProductForm{
    constructor(private id:number,
        private traderId:string,
        private name:string,
        private price:number,
        private availableStock:string,
        private available:boolean){}
}