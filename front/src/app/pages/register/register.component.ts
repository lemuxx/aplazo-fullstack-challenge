import { Component, inject } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AplazoButtonComponent } from '@apz/shared-ui/button';
import { AplazoLogoComponent } from '@apz/shared-ui/logo';
import { CostumerService } from '../../services/customer.service';
import { ModalService } from '../../services/modal.service';
import { ModalComponent } from '../../components/modal/modal.component';
import { UtilService } from '../../services/util.service';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-register',
  templateUrl: './register.component.html',
  imports: [ReactiveFormsModule, AplazoButtonComponent, AplazoLogoComponent],
})
export class RegisterComponent {

  readonly customerService = inject(CostumerService);
  readonly modalService = inject(ModalService);
  readonly utilService = inject(UtilService);
  readonly router = inject(Router);

  readonly firstName = new FormControl<string>('', {
    nonNullable: true,
    validators: [Validators.required],
  });

  readonly lastName = new FormControl<string>('', {
    nonNullable: true,
    validators: [Validators.required],
  });

  readonly secondLastName = new FormControl<string>('', {
    nonNullable: true,
    validators: [Validators.required],
  });

  readonly dateOfBirth = new FormControl<string>('', {
    nonNullable: true,
    validators: [Validators.required],
  });

  readonly form = new FormGroup({
    firstName: this.firstName,
    lastName: this.lastName,
    secondLastName: this.secondLastName,
    dateOfBirth: this.dateOfBirth,
  });

  register(): void {
    if(this.utilService.isAdult(this.form.value.dateOfBirth || '')){
      this.customerService.createCustomer(this.form.value).subscribe( response => {
        localStorage.setItem('customerId', response.id);
        this.router.navigate(['/apz/home']);
        this.modalService.open({
          title: 'Éxito',
          message: 'El cliente fue creado exitosamente'
        });
        console.log(response);
      });
    } else {
      this.modalService.open({
        title: 'Error',
        message: 'No es mayor de edad'
      });
    }
  }
}
