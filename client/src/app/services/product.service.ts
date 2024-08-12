import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  // URL de l'API pour les produits
  private apiUrl = 'http://localhost:8080/produits';

  // Subject pour gérer la liste des produits
  private productsSubject = new BehaviorSubject<any[]>([]);

  // Observable exposé pour permettre aux autres composants de s'abonner aux produits
  products$ = this.productsSubject.asObservable();

  // Constructeur avec injection du client HTTP
  constructor(private http: HttpClient) {
    this.loadProducts(); // Charge la liste des produits lors de l'initialisation du service
  }

  /**
   * Charge les produits depuis l'API et met à jour le subject.
   */
  private loadProducts() {
    this.http.get<any[]>(this.apiUrl).subscribe(data => {
      this.productsSubject.next(data); // Met à jour la liste des produits avec les données reçues
    });
  }

  /**
   * Retourne un Observable pour obtenir la liste des produits.
   *
   * @return Observable contenant la liste des produits
   */
  getProducts(): Observable<any[]> {
    return this.products$;
  }

  /**
   * Ajoute un nouveau produit via l'API et recharge la liste des produits.
   *
   * @param product Le produit à ajouter
   * @return Observable contenant le produit ajouté
   */
  addProduct(product: any): Observable<any> {
    return this.http.post(this.apiUrl, product).pipe(
      tap(() => this.loadProducts()) // Refresh la liste des produits après ajout
    );
  }
}