import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = 'http://localhost:8080/produits';
  private productsSubject = new BehaviorSubject<any[]>([]);
  products$ = this.productsSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadProducts();
  }

  private loadProducts() {
    this.http.get<any[]>(this.apiUrl).subscribe(data => {
      this.productsSubject.next(data);
    });
  }

  getProducts(): Observable<any[]> {
    return this.products$;
  }

  addProduct(product: any): Observable<any> {
    return this.http.post(this.apiUrl, product).pipe(
      tap(() => this.loadProducts())
    );
  }
}