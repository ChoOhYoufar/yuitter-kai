import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/switchMap';

export abstract class Stream<T> {

  protected _data: BehaviorSubject<Readonly<T>>;
  protected data: Observable<Readonly<T>>;

  constructor() {}

  protected abstract _fetchStream(...args: any[]): Observable<Readonly<T>>;

  // NOTE ドット３つはspread operator -> http://analogic.jp/spread-operator/
  fetchStream(...args: any[]): Observable<Readonly<T>> {
    return this._fetchStream(...args)
      .switchMap((data: T) => {
        if (this._data) {
          this._data.next(data);
          return this.data;
        } else {
          this._data = new BehaviorSubject(data);
          this.data = this._data.asObservable();
          return this.data;
        }
      });
  }

  getStream(...args: any[]): Observable<Readonly<T>> {
    return this.data || this.fetchStream(...args);
  }
}
