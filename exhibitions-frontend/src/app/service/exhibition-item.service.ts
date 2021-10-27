import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ExhibitionItem } from '../model/exhibitionItem';
import { ExhibitionSubItem } from '../model/exhibitionSubItem';

@Injectable({
  providedIn: 'root'
})
export class ExhibitionItemService {

  private exhibitionsItemsUrl = 'api/item';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };


  private sessionDataKey = "exhibition"
//   getExhibitionItem(id: number): Observable<ExhibitionItem> {
//     const item = mockExhibitionItems.find(h => h.id === id)!;
//     console.log(item)
//     return of(item)
//  }
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

  addExhibitionSubItem(exhibitionItemId: Number, subitem : ExhibitionSubItem): Observable<ExhibitionItem> {
    console.log("sending item to  " + this.exhibitionsItemsUrl + '/addsubitem/' + exhibitionItemId)
    console.log(subitem)
    return this.http.post<ExhibitionItem>(this.exhibitionsItemsUrl + '/' + exhibitionItemId + '/addsubitem', subitem , this.httpOptions).pipe(
      
      catchError(this.handleError<ExhibitionItem>('add item exhibitions'))
    )
  }

  removeExhibitionSubItem(exhibitionItemId: Number,exhibitionSubItemId: Number): Observable<ExhibitionItem> {
    console.log("deleting sub item  " + this.exhibitionsItemsUrl + '/' + exhibitionItemId + '/subitem/' + exhibitionSubItemId)
    return this.http.delete<ExhibitionItem>(this.exhibitionsItemsUrl + '/' + exhibitionItemId + '/subitem/' + exhibitionSubItemId , this.httpOptions).pipe(
      
      catchError(this.handleError<ExhibitionItem>('add item exhibitions'))
    )
  }


  constructor(
    private http: HttpClient
  ) { }
}
