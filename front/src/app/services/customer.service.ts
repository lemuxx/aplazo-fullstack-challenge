import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root',
})
export class CostumerService {

    readonly apiUrl = environment.apiUrl;
    
    readonly http = inject(HttpClient);

    createCustomer(customer: any): Observable<any>{
        return this.http.post<any>(`${this.apiUrl}customers`, customer);

    }

}