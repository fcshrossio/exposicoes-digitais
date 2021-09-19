import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { HttpClient, HttpHeaders } from '@angular/common/http';

import { mockExhibitions } from '../mock-exhibitions';
import { catchError, map, tap } from 'rxjs/operators';
import { Exhibition } from '../model/exhibition';

@Injectable({
  providedIn: 'root'
})
export class ExhibitionService {

  private exhibitionsUrl = 'api/heroes';  // URL to web api


  getExhibitions(): Observable<Exhibition[]>
  {
    console.log(this.http.get<String>("/api/hello"))
   //const exhibitions = of(mockExhibitions)
   //return exhibitions
   return  this.http.get<Exhibition[]>('api/exhibition/public')  .pipe(
      catchError(this.handleError<Exhibition[]>('getexhibitions', []))
    );
  }


  getExhibition(id: number): Observable<Exhibition> {
     const exhibition = mockExhibitions.find(h => h.id === id)!;
     console.log(exhibition)
     return of(exhibition)
  }

  createExhibition(exhibition: Exhibition) {
    mockExhibitions.push(exhibition)
  }

  createSessionExhibition(exhibition: Exhibition) {
    sessionStorage.setItem("exhibition", JSON.stringify(exhibition))
  }

  getSessionExhibition() {

      let json : string = <string>sessionStorage.getItem("exhibition")
      var exhibition = <Exhibition>JSON.parse(json) 

    return exhibition
   
  }

  saveSessionExhibition(exhibition: Exhibition) {
    console.log(exhibition)
    sessionStorage.setItem("exhibition", JSON.stringify(exhibition))
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
