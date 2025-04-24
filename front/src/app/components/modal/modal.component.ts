import { Component } from '@angular/core';

import { Observable } from 'rxjs';
import { ModalData, ModalService } from '../../services/modal.service';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-modal',
  imports: [CommonModule],
  templateUrl: './modal.component.html',
})
export class ModalComponent {
  isOpen$ = this.modalService.isOpen$;
  modalData$: Observable<ModalData | null> = this.modalService.modalData$;

  constructor(public modalService: ModalService) {}

  close() {
    this.modalService.close();
  }
}
