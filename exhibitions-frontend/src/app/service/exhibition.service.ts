import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { HttpClient, HttpHeaders } from '@angular/common/http';

import { catchError, map, tap } from 'rxjs/operators';
import { Exhibition } from '../model/exhibition';

@Injectable({
  providedIn: 'root'
})
export class ExhibitionService {

  private exhibitionsUrl = 'api/exhibition';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };


  private sessionDataKey = "exhibition"


  getExhibitions(): Observable<Exhibition[]>
  {
   return  this.http.get<Exhibition[]>(this.exhibitionsUrl)  .pipe(
     
      catchError(this.handleError<Exhibition[]>('getexhibitions', []))
    );
  }


  getExhibition(id: number): Observable<Exhibition> {
    // const exhibition = mockExhibitions.find(h => h.id === id)!;
    return  this.http.get<Exhibition>(this.exhibitionsUrl+id)  .pipe(
     
      catchError(this.handleError<Exhibition>('getexhibitions'))
    );
  }

  createExhibition(exhibition: Exhibition) {
    //mockExhibitions.push(exhibition)
    return this.http.post<Exhibition>(this.exhibitionsUrl, exhibition, this.httpOptions).subscribe({
      next: data => {
          //this. = data.id;
      },
      error: error => {
          //this.errorMessage = error.message;
          console.error('There was an error!', error);
      }
  })
  }

  updateExhibition(exhibition: Exhibition) {
    console.log("update exhibitions")
  }


  createSessionExhibition(exhibition: Exhibition) {
    sessionStorage.setItem(this.sessionDataKey, JSON.stringify(exhibition))
  }

  getSessionExhibition() {

      let json : string = <string>sessionStorage.getItem(this.sessionDataKey)
      var exhibition = <Exhibition>JSON.parse(json) 

    return exhibition
   
  }

  saveSessionExhibition(exhibition: Exhibition) {
    console.log(exhibition)
    sessionStorage.setItem(this.sessionDataKey, JSON.stringify(exhibition))
  }

  clearSessionExhibition() {
    console.log("clear session data")
    sessionStorage.removeItem(this.sessionDataKey)
  }

  publishSessionExhibition() {
    console.log("publish session data")
    var exhibition = this.getSessionExhibition();
    exhibition.changeStatus("PUBLIC");
    this.saveSessionExhibition(exhibition)

  }

  saveExhibitionAsDraft() {
    var exhibition = this.getSessionExhibition();
    this.updateExhibition(exhibition)
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
