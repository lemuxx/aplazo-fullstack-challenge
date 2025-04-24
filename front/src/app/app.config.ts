import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { requestInterceptor } from './config/interceptors/request.interceptor';
import { responseInterceptor } from './config/interceptors/response.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(
      withInterceptors(
        [
          requestInterceptor,
          responseInterceptor
        ]
      )
    ),
    provideRouter(routes),
  ],
};
