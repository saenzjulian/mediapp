import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; 
import { LoginService } from 'src/app/_services/login.service';
import { environment } from 'src/environments/environment'; 
import '../../../assets/login-animation.js'; //importación de animación

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario: string;
  clave: string;
  mensaje: string;
  error: string;

  constructor(
    private loginService : LoginService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  iniciarSesion(){
    this.loginService.login(this.usuario, this.clave).subscribe(data => {
      // data es todo el token de acceso con refesh token y demás campos
      /**
       * Puedo almacenar data en local storage ()perece incluso hasta si apago la máquina
       * o en la session storage, que es más volatil (solo en la pestaña)
       */
      sessionStorage.setItem(environment.TOKEN_NAME, data.access_token);

      this.router.navigate(['pages/inicio']);
    });
  }

  ngAfterViewInit() {
    /**
     * Cuando se termine de cargar la vista 
     * quiero tener el método initialize que está en el archivo js
     * que se encarga de al animación
     */
    (window as any).initialize();
  }

}
