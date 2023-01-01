import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../model/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  public getAllEmployees(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(`${this.baseUrl}`);
  }

  public getEmployeeById(id: number): Observable<Employee> {
    return this.httpClient.get<Employee>(`${this.baseUrl}/${id}`);
  }

  public createEmployee(employee: Employee): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}`, employee);
  }

  public updateEmployee(id: number, employee: Employee): Observable<Object> {
    return this.httpClient.put(`${this.baseUrl}/${id}`, employee);
  }

  public deleteEmployee(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/${id}`);
  }

}
