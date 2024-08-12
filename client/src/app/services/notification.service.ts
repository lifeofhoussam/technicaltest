import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private messageSubject = new BehaviorSubject<string | null>(null);

  message$ = this.messageSubject.asObservable();

  /**
   * Affiche un message de notification pendant une durée spécifiée.
   *
   * @param message Le message à afficher
   * @param duration La durée d'affichage du message en millisecondes (2 secondes)
   */
  showMessage(message: string, duration: number = 2000): void {
    // Met à jour le Subject avec le nouveau message
    this.messageSubject.next(message);

    // Efface le message après la durée spécifiée
    setTimeout(() => {
      this.messageSubject.next(null);
    }, duration);
  }
}
