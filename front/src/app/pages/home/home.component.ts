import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AplazoButtonComponent } from '@apz/shared-ui/button';
import { AplazoLogoComponent } from '@apz/shared-ui/logo';
import { LoanService } from '../../services/loan.service';
import { ModalService } from '../../services/modal.service';

@Component({
  standalone: true,
  selector: 'app-home',
  templateUrl: './home.component.html',
  imports: [
    ReactiveFormsModule, AplazoButtonComponent, AplazoLogoComponent
  ]
})
export class HomeComponent {

  readonly loanService = inject(LoanService);
  readonly modalService = inject(ModalService);

  ngOnInit(): void {
   this.form.patchValue({ customerId: localStorage.getItem('customerId') ?? ''});
  }

  readonly customerId = new FormControl<string>('', {
    nonNullable: true,
    validators: [Validators.required],
  });

  readonly amount = new FormControl<number>(0, {
    nonNullable: true,
    validators: [Validators.required],
  });

  readonly form = new FormGroup({
    customerId: this.customerId,
    amount: this.amount,
  });

  newLoan(){
    this.loanService.createLoan(this.form.value).subscribe(response => {
      this.modalService.open({
        title: 'Éxito',
        message: 'El prestamo fue creado exitosamente'
      });
    });
  }
}
