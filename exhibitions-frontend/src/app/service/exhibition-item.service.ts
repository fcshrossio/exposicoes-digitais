import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ExhibitionItem } from '../model/exhibitionItem';

@Injectable({
  providedIn: 'root'
})
export class ExhibitionItemService {

//   getExhibitionItem(id: number): Observable<ExhibitionItem> {
//     const item = mockExhibitionItems.find(h => h.id === id)!;
//     console.log(item)
//     return of(item)
//  }


  constructor() { }
}
