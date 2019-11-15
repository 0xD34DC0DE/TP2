import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Traders } from '../models/account';

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

  getTraderProducts(email: string) : Observable<Product[]> {
    return this.http.get<Product[]>(`${environment.traderUrl}/${email}/products`);
  }

  getAllTraders() : Observable<Traders[]> {
    return this.http.get<Traders[]>(`${environment.traderUrl}/all`);
  }

  updateTrader(trader: Traders) : Observable<Traders> {
    return this.http.put<Traders>(`${environment.traderUrl}/load`, trader);
  }

  deleteTrader(email: string) : Observable<void> {
    return this.http.delete<void>(`${environment.traderUrl}/${email}`);
  }
}
