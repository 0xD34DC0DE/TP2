import { Status } from './status-enum';

export class Account{
    constructor(public email:string,
        public firstName:string,
        public lastName:string,
        public phone:string,
        public role: string,
        public status: Status,
        public password?: string) {}
}
export interface AccountInter{
    email:string;
    firstName:string;
    lastName:string;
    phone:string;
    type:string;
    role: string;
    status:Status;
}

export class AccountForm{
    constructor(private email:string,
        private firstName:string,
        private lastName:string,
        private phone:string){}
}

export class AccountUpdateForm{
    constructor(
        private firstName:string,
        private lastName:string,
        private phone:string){}
}