import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
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
    if(this.edicion){
      // Update
    }else{
      // Create
    }
  }

}
