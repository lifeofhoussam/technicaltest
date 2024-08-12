import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {
  // Message à afficher dans la notification, initialement null
  message: string | null = null;

  // Injection du service de notification pour gérer les messages
  constructor(private notificationService: NotificationService) {}

  ngOnInit(): void {
    this.notificationService.message$.subscribe(message => {
      // Met à jour le message à afficher lorsqu'un nouveau message est reçu
      this.message = message;
    });
  }
}