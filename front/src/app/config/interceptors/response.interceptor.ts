import { HttpInterceptorFn, HttpResponse } from '@angular/common/http';
import { tap } from 'rxjs';

export const responseInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    tap(event => {
      if (event instanceof HttpResponse) {
        if (req.url.includes('/customers') && req.method === 'POST'){
            const header = event.headers.get('X-Auth-Token');
            localStorage.setItem('token', header ?? '')
        }
      }
    })
  );
};