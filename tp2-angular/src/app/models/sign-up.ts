export class SignUpForm {
    constructor(private firstName: string,
        private lastName: string,
        private email: string,
        private password: string,
        private phone: string,
        private roles: string[],
        private adresse: string) { }
}