import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root',
})
export class UtilService {

    isAdult(age: string) {
        const birthDate = new Date(age);
        const today = new Date();
        let ageToday = today.getFullYear() - birthDate.getFullYear();
        const month = today.getMonth() - birthDate.getMonth();
        const day = today.getDate() - birthDate.getDate();
        if (month < 0 || (month === 0 && day < 0)) {
            ageToday--;
        }
        return ageToday >= 18;
    }

}