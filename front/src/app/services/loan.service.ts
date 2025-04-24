import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root',
})
export class LoanService {

    readonly apiUrl = environment.apiUrl;
    
    readonly http = inject(HttpClient);

    createLoan(loan: any): Observable<any>{
        return this.http.post<any>(`${this.apiUrl}loans`, loan);

    }

}