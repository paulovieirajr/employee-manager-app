import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Employee } from 'src/app/model/employee';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.scss'],
})
export class EmployeesComponent implements OnInit {
  public employees: Employee[] = [];
  public editEmployee!: Employee;
  public deleteEmployee!: Employee;
  public employeeForm: FormGroup = this.formBuilder.group({
    id: [null],
    name: [null, [Validators.required]],
    email: [null, [Validators.required, Validators.email]],
    jobTitle: [null, [Validators.required]],
    phone: [null, [Validators.required]],
    imageUrl: [null, [Validators.required]],
  });

  constructor(
    private employeeService: EmployeeService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit() {
    this.getEmployees();
  }

  showId(employee: Employee): void {
    console.log(employee);
  }

  onOpenModal(employee: Employee, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    if (mode === 'add') {
      this.employeeForm.reset();
      button.setAttribute('data-target', '#addEmployeeModal');
    }
    if (mode === 'edit') {
      this.patchEmployeeForm(employee);

      console.log(this.employeeForm.value);
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if (mode === 'delete') {
      this.patchEmployeeForm(employee);

      button.setAttribute('data-target', '#deleteEmployeeModal');
    }

    container?.appendChild(button);
    button.click();
  }

  private patchEmployeeForm(employee: Employee) {
    this.employeeForm.patchValue({
      id: employee.id,
      name: employee.name,
      email: employee.email,
      jobTitle: employee.jobTitle,
      phone: employee.phone,
      imageUrl: employee.imageUrl,
    });
  }

  public onAddEmployee(): void {
    this.employeeService.createEmployee(this.employeeForm.value).subscribe({
      next: (employee) => {
        console.log(employee);
        this.getEmployees();
      },
      error: (err: HttpErrorResponse) => {
        window.alert(err.message);
      },
    });

    // Close the modal window
    document.getElementById('add-employee-form')?.click();
  }

  public onUpdateEmployee(): void {
    this.employeeService
      .updateEmployee(this.employeeForm.value.id, this.employeeForm.value)
      .subscribe({
        next: (employee) => {
          console.log(employee);
          this.getEmployees();
        },
        error: (err: HttpErrorResponse) => {
          window.alert(err.message);
        },
      });

    // Close the modal window
    document.getElementById('close-update-employee-modal')?.click();
  }

  public onDeleteEmployee(): void {
    const id: number = this.employeeForm.value.id;

    this.employeeService.deleteEmployee(id).subscribe(
      (response: void) => {
        console.log(response);
        this.getEmployees();
      }
    );

    // Close the modal window
    document.getElementById('close-delete-employee-modal-yes')?.click();
    document.getElementById('close-delete-employee-modal-no')?.click();
  }

  public searchEmployees(key: string): void {
    console.log(key);
    const results: Employee[] = [];
    for (const employee of this.employees) {
      if (employee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.phone.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.jobTitle.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(employee);
      }
    }
    this.employees = results;
    if (results.length === 0 || !key) {
      this.getEmployees();
    }
  }

  public getEmployees(): void {
    this.employeeService.getAllEmployees().subscribe({
      next: (employees) => (this.employees = employees),
      error: (err: HttpErrorResponse) => {
        window.alert(err.message);
      },
      complete: () => console.log('Completed'),
    });
  }
}
