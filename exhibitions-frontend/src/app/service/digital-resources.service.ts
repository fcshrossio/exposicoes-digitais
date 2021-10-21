import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { DigitalResource } from '../model/digitalResource';

@Injectable({
  providedIn: 'root'
})
export class DigitalResourcesService {

  private exhibitionsUrl = 'api/resource';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getResources(): Observable<DigitalResource[]>
  {
   return  this.http.get<DigitalResource[]>(this.exhibitionsUrl)  .pipe(
     
      catchError(this.handleError<DigitalResource[]>('getexhibitions', []))
    );
  }


  
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      //this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  constructor(
    private http: HttpClient
  ) { }
}
