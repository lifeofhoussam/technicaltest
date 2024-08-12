import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {
  // Formulaire pour ajouter un produit
  productForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private notificationService: NotificationService
  ) {
    // Initialisation du formulaire
    this.productForm = this.fb.group({
      nom: ['', Validators.required], // Champ nom requis
      prix: ['', [Validators.required, Validators.min(1)]] // Champ prix requis avec une valeur minimale de 1
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {

    if (this.productForm.valid) {
      // Appelle le service pour ajouter un produit
      this.productService.addProduct(this.productForm.value).subscribe({
        // Si l'ajout réussit, réinitialise le formulaire
        next: () => {
          this.productForm.reset();
        },
        // En cas de produit existant, affiche le message d'erreur
        error: () => {
          this.notificationService.showMessage(`Un produit avec le même nom existe déjà!`);
        }
      });
    }
  }
}