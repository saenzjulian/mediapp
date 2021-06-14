import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Paciente } from 'src/app/_models/paciente';
import { PacienteService } from 'src/app/_services/paciente.service';

@Component({
  selector: 'app-paciente-edicion',
  templateUrl: './paciente-edicion.component.html',
  styleUrls: ['./paciente-edicion.component.css']
})
export class PacienteEdicionComponent implements OnInit {

  form: FormGroup;
  id: number;
  edicion: boolean;

  constructor(
    private root: ActivatedRoute,
    private router: Router,
    private pacienteService: PacienteService
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      'id': new FormControl(0),
      'nombres': new FormControl(''),
      'apellidos': new FormControl(''),
      'dni': new FormControl(''),
      'telefono': new FormControl(''),
      'direccion': new FormControl(''),
      'email': new FormControl('')
    });

    /**
     * this.root.params me recupera la ruta actual en la que estoy parado
     * para este caso es paciente/id
     **/
    this.root.params.subscribe(data => {
      this.id = data['id']
      this.edicion = data['id'] != null // this.id > 0 ? true : false
      this.initForm()
    }) 
  }

  initForm(){
    if(this.edicion){
      // Update
      this.pacienteService.listarById(this.id).subscribe(data =>{
        this.form = new FormGroup({
          'id': new FormControl(data.idPaciente),
          'nombres': new FormControl(data.nombres),
          'apellidos': new FormControl(data.apellidos),
          'dni': new FormControl(data.dni),
          'telefono': new FormControl(data.telefono),
          'direccion': new FormControl(data.direccion),
          'email': new FormControl(data.email)
        });
      })
    }
    
  }

  operar() {
    let paciente = new Paciente();
    paciente.idPaciente = this.form.value['id'];
    paciente.nombres = this.form.value['nombres'];
    paciente.apellidos = this.form.value['apellidos'];
    paciente.dni = this.form.value['dni'];
    paciente.telefono = this.form.value['telefono'];
    paciente.direccion = this.form.value['direccion'];
    paciente.email = this.form.value['email'];

    if(this.edicion){
      // Update
      this.pacienteService.modificar(paciente).subscribe(() => {
        this.pacienteService.listar().subscribe(data => {
          this.pacienteService.pacienteCambio.next(data)
          this.pacienteService.mensajeCambio.next("Paciente Actualizado")
        })
      });
      
    }else{
      // Create
      this.pacienteService.registrar(paciente).subscribe(() => {
        this.pacienteService.listar().subscribe(data => {
          this.pacienteService.pacienteCambio.next(data)
          this.pacienteService.mensajeCambio.next("Paciente Creado")
        })
      });
    }
    this.router.navigate(['pages/paciente'])
  }

}
