import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { HttpClient, HttpHeaders } from '@angular/common/http';

import { catchError, map, tap } from 'rxjs/operators';
import { Exhibition } from '../model/exhibition';
import { Credits } from '../model/credits';
import { ExhibitionItem } from '../model/exhibitionItem';
import { Marker } from '../model/marker';

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
    return  this.http.get<Exhibition>(this.exhibitionsUrl+ '/' + id)  .pipe(
     
      catchError(this.handleError<Exhibition>('getexhibitions'))
    );
  }

  createExhibition(exhibition: Exhibition): Observable<Exhibition> {
    //mockExhibitions.push(exhibition)
    return this.http.post<Exhibition>(this.exhibitionsUrl, exhibition, this.httpOptions).pipe(

      catchError(this.handleError<Exhibition>('getexhibition'))
    )
  }

  updateExhibition(exhibition: Exhibition): Observable<Exhibition> {
    console.log("sending update request to " + this.exhibitionsUrl + '/' + exhibition.id)
    return this.http.post<Exhibition>(this.exhibitionsUrl + "/" + exhibition.id , exhibition, this.httpOptions).pipe(
      
      catchError(this.handleError<Exhibition>('updateexhibitions'))
    )
  }

  updateExhibitionCredits(exhibition: Exhibition): Observable<Exhibition> {
    console.log("sending update request to " + this.exhibitionsUrl + '/' + exhibition.id  + '/credits')
    var credits = new Credits(exhibition.credits)
    console.log(credits)
    return this.http.post<Exhibition>(this.exhibitionsUrl + '/' + exhibition.id + '/credits' , credits , this.httpOptions).pipe(
      
      catchError(this.handleError<Exhibition>('updateexhibitions'))
    )
  }

  addExhibitionItem(exhibitionId: Number, item : ExhibitionItem): Observable<ExhibitionItem> {
    console.log("sending item to  " + this.exhibitionsUrl + '/additem/' + exhibitionId)
    return this.http.post<ExhibitionItem>(this.exhibitionsUrl + '/additem/' + exhibitionId, item , this.httpOptions).pipe(
      
      catchError(this.handleError<ExhibitionItem>('add item exhibitions'))
    )
  }

  addExhibitionMarker(exhibitionId: Number, item : Marker): Observable<Marker> {
    console.log("sending item to  " + this.exhibitionsUrl + '/' + exhibitionId + '/addmarker')
    return this.http.post<Marker>(this.exhibitionsUrl + '/' + exhibitionId + '/addmarker', item , this.httpOptions).pipe(
      
      catchError(this.handleError<Marker>('add marker exhibitions'))
    )
  }

  removeExhibitionMarker(exhibitionId: Number, markerId : Number): Observable<Exhibition> {
    console.log("sending item to  " + this.exhibitionsUrl + '/' + exhibitionId + '/marker/' + markerId)
    return this.http.delete<Exhibition>(this.exhibitionsUrl + '/' + exhibitionId + '/marker/' + markerId  , this.httpOptions).pipe(
      
      catchError(this.handleError<Exhibition>('remove marker exhibitions'))
    )
  }

  deleteExhibition(exhibitionId: Number): Observable<any> {
    console.log("sending item to  " +  this.exhibitionsUrl + '/' + exhibitionId)
    return this.http.delete<any>(this.exhibitionsUrl + '/' + exhibitionId, this.httpOptions).pipe(
      
      catchError(this.handleError<any>('delete exhibition'))
    )
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
