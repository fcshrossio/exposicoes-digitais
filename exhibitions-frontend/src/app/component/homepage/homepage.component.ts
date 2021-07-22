import { Component, OnInit } from '@angular/core';

import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { LoginFormComponent } from '../form/login-form/login-form.component';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private modalService: NgbModal) { }

  open() {
    const modalRef = this.modalService.open(LoginFormComponent);
    modalRef.componentInstance.name = 'World';
  }

  ngOnInit(): void {
  }

}
