import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})

export class ProductListComponent implements OnInit {
  // Tableau pour stocker la liste des produits
  products: any[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    // Appelle le service pour obtenir la liste des produits
    this.productService.getProducts().subscribe(data => {
      // Stocke les produits récupérés dans le tableau
      this.products = data;
    });
  }
}