import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product, editProductForm, NewProduct } from '../models/product';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Account } from '../models/account';
import { Status } from '../models/status-enum';

@Injectable({
  providedIn: 'root'
})
export class TraderService {

  constructor(private http: HttpClient) { }

  getProducts(id: string) {
    return this.http.get<Product>(`${environment.productsUrl}/${id}`);
  }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${environment.productsUrl}/all`);
  }

  getAllAvailableProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${environment.productsUrl}/allAvailable`);
  }

  getTraderProducts(email: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${environment.traderUrl}/${email}/products`);
  }

  getAllTraders(): Observable<Account[]> {
    return this.http.get<Account[]>(`${environment.traderUrl}/all`);
  }

  updateTrader(trader: Account): Observable<Account> {
    return this.http.put<Account>(`${environment.traderUrl}/${trader.email}`, trader);
  }

  setStatusTrader(email: string, traderStatus: Status): Observable<boolean> {
    return this.http.put<boolean>(`${environment.traderUrl}/${email}/status`, { status: traderStatus });
  }

  deleteTrader(email: string): Observable<void> {
    return this.http.delete<void>(`${environment.traderUrl}/${email}`);
  }

  deleteProduct(email: string, id: number): Observable<void> {
    return this.http.delete<void>(`${environment.traderUrl}/${email}/products/${id}`);
  }

  updateProduct(email: string, product: editProductForm): Observable<Product> {
    return this.http.put<Product>(`${environment.traderUrl}/${email}/products`, product);
  }

  createProduct(email: string, product: NewProduct): Observable<Product> {
    return this.http.post<Product>(`${environment.traderUrl}/${email}/products`, product);
  }
}
