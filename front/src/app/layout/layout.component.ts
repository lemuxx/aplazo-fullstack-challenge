import { Component, inject } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterLink, RouterOutlet } from '@angular/router';
import { AplazoButtonComponent } from '@apz/shared-ui/button';
import { AplazoDashboardComponents } from '@apz/shared-ui/dashboard';
import { AplazoSidenavLinkComponent } from '@apz/shared-ui/sidenav';
import { ROUTE_CONFIG } from '../config/routes.config';
import { filter } from 'rxjs';

@Component({
  standalone: true,
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  imports: [
    AplazoDashboardComponents,
    AplazoButtonComponent,
    AplazoSidenavLinkComponent,
    RouterOutlet,
    RouterLink
  ],
})
export class LayoutComponent {
  readonly #router = inject(Router);
  readonly #ARout = inject(ActivatedRoute);
  readonly appRoutes = ROUTE_CONFIG;
  title = "Inicio";

  ngOnInit(): void {
    this.#router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      let currentRoute = this.#ARout.root;
      while (currentRoute.firstChild) {
        currentRoute = currentRoute.firstChild;
      }
      this.title = currentRoute.snapshot.data['title'];
      console.log(currentRoute.snapshot.data['title']);
    });
    
  }

  logout(){
    localStorage.removeItem('token');
    this.#router.navigate(['/auth']);
  }

  clickLogo(): void {
    this.#router.navigate(['/apz/home']);
  }
}
