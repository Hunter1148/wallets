import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  formModel = {
    name: '',
    limit: 9000,
    level: {text: 'Master',id:4}
  };
  levels =[
    {text:'noob', id : 1},
    {text:'Expert',id : 2},
    {text: 'Master',id : 4}]

  constructor() {
  }

  ngOnInit() {
  }

  createNewWallet() {
    console.log(this.formModel);

  }

  isDisabled() {
    return this.formModel.name ==='';


  }
}
