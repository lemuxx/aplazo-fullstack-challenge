import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface ModalData {
  title: string;
  message: string;
}

@Injectable({
  providedIn: 'root',
})
export class ModalService {
  private isOpenSubject = new BehaviorSubject<boolean>(false);
  isOpen$ = this.isOpenSubject.asObservable();

  private modalDataSubject = new BehaviorSubject<ModalData | null>(null);
  modalData$ = this.modalDataSubject.asObservable();

  open(data: ModalData) {
    this.modalDataSubject.next(data);
    this.isOpenSubject.next(true);
  }

  close() {
    this.isOpenSubject.next(false);
    this.modalDataSubject.next(null);
  }
}
