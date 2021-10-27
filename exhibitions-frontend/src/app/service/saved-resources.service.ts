import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { SavedResources } from '../model/savedResources';

@Injectable({
  providedIn: 'root'
})
export class SavedResourcesService {

  private savedresourcesUrl = 'api/savedresources';  // URL to web api

  getSavedResourcesLists(): Observable<SavedResources[]>
  {
   return  this.http.get<SavedResources[]>(this.savedresourcesUrl)  .pipe(
     
      catchError(this.handleError<SavedResources[]>('getsaved resources', []))
    );
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };


  private sessionDataKey = "exhibition"

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
