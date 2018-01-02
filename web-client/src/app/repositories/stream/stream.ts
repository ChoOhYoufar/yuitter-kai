import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/switchMap';

export abstract class Stream<T> {

  protected _data: BehaviorSubject<T>;
  protected data: Observable<T>;

  private previousArgs: any[] | undefined;

  constructor() {}

  protected abstract _fetchStream(...args: any[]): Observable<T>;

  // NOTE ドット３つはspread operator -> http://analogic.jp/spread-operator/
  fetchStream(...args: any[]): Observable<T> {
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

  getStream(...args: any[]): Observable<T> {
    return this.data || this.fetchStream(...args);
  }

  refetch(): void {
    const args = this.previousArgs ? this.previousArgs : [];
    this.fetchStream(...args).subscribe();
  }
}
