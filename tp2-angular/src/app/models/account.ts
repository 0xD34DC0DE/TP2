import { Status } from './status-enum';

export class Account{
    constructor(public email:string,
        public firstName:string,
        public lastName:string,
        public address:string,
        public phone:string,
        public roles: string[],
        public status: Status,
        public password?: string) {}
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

export class AccountUpdateForm{
    constructor(
        private firstName:string,
        private lastName:string,
        private address:string,
        private phone:string){}
}